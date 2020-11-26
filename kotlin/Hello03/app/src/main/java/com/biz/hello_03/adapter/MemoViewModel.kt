package com.biz.hello_03.adapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.biz.hello_03.db.MemoRepository
import com.biz.hello_03.model.MemoVO

class MemoViewModel(app: Application) : AndroidViewModel(app) {
    private val memoRep: MemoRepository = MemoRepository(app)
    private lateinit var memoList: LiveData<MutableList<MemoVO>>

    init {
        memoList = memoRep.selectAll()
    }

    fun selectAll(): LiveData<MutableList<MemoVO>> {
        return memoList
    }

    fun save(memoVO: MemoVO) {
        memoRep.save(memoVO)
    }
}