package github.sun5066.todov1

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import github.sun5066.todov1.adapter.TodoRecyclerAdapter
import github.sun5066.todov1.adapter.TodoViewModel
import github.sun5066.todov1.databinding.ActivityMainBinding
import github.sun5066.todov1.model.TodoVO
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewAdapter: TodoRecyclerAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.btnSave.setOnClickListener {
            this.todoSave(it)
        }

        binding.inputText.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    this.todoSave(v)
                    true
                }
                else -> false
            }
        }

        val todoList: MutableList<TodoVO> = mutableListOf()
        todoViewAdapter = TodoRecyclerAdapter(todoList)

        todoViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(TodoViewModel::class.java)

        todoViewModel.selectAll().observe(this, Observer {
            it?.let { todoViewAdapter.setList(it) }
            todoViewAdapter.notifyDataSetChanged()
        })

        val recyclerView = binding.recyclerView
        recyclerView.adapter = todoViewAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun todoSave(view: View) {
        if (binding.inputText.text.toString().isEmpty()) {
            Snackbar
                .make(view, "메모를 적으세요!", Snackbar.LENGTH_LONG)
                .show()
            return
        }

        val todoVO = TodoVO()
        var sd = SimpleDateFormat("yyyy년 MM월 dd일 E요일")
        var st = SimpleDateFormat("HH시 mm분 ss초")
        var date = System.currentTimeMillis()

        todoVO.date = sd.format(date)
        todoVO.time = st.format(date)
        todoVO.todo = binding.inputText.text.toString()
        todoViewModel?.save(todoVO)
        binding.inputText.text = null
    }
}