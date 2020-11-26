package com.biz.hello_03

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.biz.hello_03.adapter.MemoViewAdapter
import com.biz.hello_03.adapter.MemoViewModel
import com.biz.hello_03.model.MemoVO
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

        txtMemoInput = findViewById(R.id.txt_memo)
        btnSave = findViewById(R.id.btn_save)
        memoViewModel = MemoViewModel(this.application)

        // 입력 도중 키보드의 Send 버튼을 클릭했을때 반응할 event
        txtMemoInput.setOnEditorActionListener { view, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
//                    Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
                    val text: String = txtMemoInput.text.toString()
                    val sd = SimpleDateFormat("yyyy-MM-dd")
                    val st = SimpleDateFormat("HH:mm:ss")
                    val date = Date(System.currentTimeMillis())
                    val memoVO: MemoVO = MemoVO()
                    memoViewModel.save(memoVO)
                    true
                }
                else -> false
            }
        }

        btnSave.setOnClickListener { view ->
            Log.d("btnSaveClick", "onCreate: onClicked!")
            val text: String = txtMemoInput.text.toString()
//            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        }

        var memoList: MutableList<MemoVO> = mutableListOf();
        memoAdapter = MemoViewAdapter(this, memoList)

        // ================================================
        // recyclerView 와 데이터를 바인딩하는 코드
        // ================================================
        // 내용물이 없는 mutableList 선언 및 초기화, null 값이 되지 않도록 하기 위한 조치

//        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
//        memoViewModel.selectAll()?.observe(this, { voList ->
//            if (voList != null) {
//                memoAdapter.setList(voList)
//            }
//            memoAdapter.notifyDataSetChanged()
//        })
//        memoViewModel
//            .selectAll()?.observe(this, { voList ->
//                if (voList)
//            })

        val rView: RecyclerView = findViewById(R.id.data_list_view)
        rView.adapter = memoAdapter

        val layoutManager = LinearLayoutManager(this)
        rView.layoutManager = layoutManager
    }
}