package github.sun5066

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import github.sun5066.adapter.TodoViewAdapter
import github.sun5066.adapter.TodoViewModel
import github.sun5066.model.TodoVO
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private lateinit var txtTodoInput: TextInputEditText
    private lateinit var btnAdd: Button
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoViewAdapter: TodoViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTodoInput = findViewById(R.id.input_todo)
        btnAdd = findViewById(R.id.btn_add)
        todoViewModel = TodoViewModel(this.application)

        txtTodoInput.setOnEditorActionListener { view, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    this.todoAdd(view)
                    true
                }
                else -> false
            }
        }

        btnAdd.setOnClickListener { view ->
            this.todoAdd(view)
        }

        var todoList: MutableList<TodoVO> = mutableListOf()
        todoViewAdapter = TodoViewAdapter(
            todoList,
            onDelete = { id -> todoViewModel.delete(id as Long) },
            onUpdate = { id -> this.todoUpdate(id as Long) },
            onComplete = { id -> this.todoComplete(id as Long) }
        )

        todoViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(TodoViewModel::class.java)

        todoViewModel.selectAll().observe(this, {
            it?.let { todoViewAdapter.setList(it) }
            todoViewAdapter.notifyDataSetChanged()
        })

        val recyclerView: RecyclerView = findViewById(R.id.data_list_view)
        recyclerView.adapter = todoViewAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    fun todoAdd(view: View) {
        val txtTodo = txtTodoInput.text.toString()

        if (txtTodo.isEmpty()) {
            Snackbar
                .make(view, "오늘 할 일을 적어주세요!", Snackbar.LENGTH_SHORT)
                .show()
            return
        }

        var todoVO: TodoVO = TodoVO()
        if (btnAdd.text == "변경") {
            todoVO = btnAdd.tag as TodoVO
            todoVO.todo = txtTodo
            btnAdd.text = "추가"
        } else {
            val sd = SimpleDateFormat("yyyy-MM-dd")
            val st = SimpleDateFormat("HH:mm:ss")
            val date = System.currentTimeMillis()

            todoVO.todo = txtTodo
            todoVO.date = sd.format(date).toString()
            todoVO.time = st.format(date).toString()
        }
        todoViewModel.save(todoVO)
        txtTodoInput.text = null
    }

    fun todoUpdate(id: Long) {
        val todoVO: TodoVO = todoViewModel.findById(id)

        if (todoVO.isComplete) {
            Toast.makeText(this, "완료한 업무는 수정이 불가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }
        txtTodoInput?.setText(todoVO.todo.toString())
        btnAdd.text = "변경"
        btnAdd.tag = todoVO
    }

    fun todoComplete(id: Long) {
        val todoVO: TodoVO = todoViewModel.findById(id)
        todoVO.isComplete = !todoVO.isComplete
        todoViewModel.save(todoVO)
    }
}
