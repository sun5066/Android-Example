package com.biz.hello_03

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.biz.hello_03.adapter.MemoViewAdapter
import com.biz.hello_03.adapter.MemoViewModel
import com.biz.hello_03.model.MemoVO
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

/**
 * MainActivity class
 * Android 의 진입점 클래스
 * AppCompatActivity 클래스를 상속받아서 사용
 * Android 의 App이 실행하는데 필요한 기본 속성들이 정의 되어 있다.
 * */
class MainActivity : AppCompatActivity() {

    // lateinit : 지금은 변수를 선언만 하지만 "이 클래스 어딘가에서"
    // 반드시 초기화를 하겠다 라는 의미로 쓰임
    private lateinit var txtMemoInput: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var memoViewModel: MemoViewModel
    private lateinit var memoAdapter: MemoViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        txtMemoInput = findViewById(R.id.txt_memo)
        btnSave = findViewById(R.id.btn_save)
        memoViewModel = MemoViewModel(this.application)

        // 입력 도중 키보드의 Send 버튼을 클릭했을때 반응할 event 람다식
        txtMemoInput.setOnEditorActionListener { view, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
//                    Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
                    this.memoSave(view)
                    true
                }
                else -> false
            }
        }

        btnSave.setOnClickListener { view ->
            Log.d("btnSaveClick", "onCreate: onClicked!")
            val text: String = txtMemoInput.text.toString()
//            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            this.memoSave(view)
        }

        var memoList: MutableList<MemoVO> = mutableListOf();
        memoAdapter = MemoViewAdapter(
            memoList,
            onDelete = { id -> memoViewModel.delete(id as Long) },
            onUpdate = { id -> this.memoUpdate(id as Long) })

        // ================================================
        // recyclerView 와 데이터를 바인딩하는 코드
        // ================================================
        // 내용물이 없는 mutableList 선언 및 초기화, null 값이 되지 않도록 하기 위한 조치

        memoViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(MemoViewModel::class.java)

        // Rx(Reactive Extension) java 패턴을 응용해서 만든 observe 메서드
        // observer 데이터를 생산하는 주제
        // 데이터를 생산하고 view 한테, 지금 데이터가 만들어졌으니 가져다가 알아서 요리해라 라는 통보만 함.
        // 그래서 부담이 적은거같다라는 개인적인 생각임
        memoViewModel.selectAll().observe(this, {
            it?.let {
                memoAdapter.setList(it)
            }
            memoAdapter.notifyDataSetChanged()
        })

        val rView: RecyclerView = findViewById(R.id.data_list_view)
        rView.adapter = memoAdapter

        val layoutManager = LinearLayoutManager(this)
        rView.layoutManager = layoutManager
    }

    fun memoSave(view: View) {
        // Button Parent 인 View 를 Button 으로 Down 형변환 하여
        // Button 의 속성을 쉽게 추출하여 사용할 수 있도록 한다.
        val btn = view as Button

        // 입력박스에 입력된 문자열 추출
        val txtMemo = txtMemoInput.text.toString()
        if (txtMemo.isEmpty()) {
            Snackbar
                .make(view, "메모를 입력한 후 저장을 수행하세요", Snackbar.LENGTH_LONG)
                .show()
            return
        }

        var memoVO = MemoVO()
        if (btn.text == "변경") {
            memoVO = btn.tag as MemoVO
            memoVO.memo = txtMemo
        } else {
            val sd = SimpleDateFormat("yyyy-MM-dd")
            val st = SimpleDateFormat("HH:mm:ss")
            val date = Date(System.currentTimeMillis())

            memoVO.memo = txtMemo
            memoVO.date = sd.format(date).toString()
            memoVO.time = st.format(date).toString()

        }
        memoViewModel.save(memoVO)
        // 메모를 저장한 후 입력박스에 내용을 초기화 시키기
        txtMemoInput.text = null
        btn.text = "추가"
    }

    fun memoUpdate(id: Long) {
        val memoVO: MemoVO = memoViewModel.findById(id)

        // TextInputBox 에서 값을 read 할때는 text = textbox.text
        // 값을 write 할때는 textbox?.setText
        txtMemoInput?.setText(memoVO.memo as String)
        btnSave.text = "변경"
        btnSave.tag = memoVO
    }
}