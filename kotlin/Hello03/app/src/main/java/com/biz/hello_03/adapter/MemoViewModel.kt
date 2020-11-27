package com.biz.hello_03.adapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.biz.hello_03.db.MemoRepository
import com.biz.hello_03.model.MemoVO

class MemoViewModel(app: Application) : AndroidViewModel(app) {
    private val memoRep: MemoRepository = MemoRepository(app)

    fun selectAll(): LiveData<MutableList<MemoVO>> {
        return memoRep.selectAll()
    }

    fun findById(id: Long): MemoVO {
        return memoRep.findById(id)
    }

    fun save(memoVO: MemoVO) {
        memoRep.save(memoVO)
    }

    fun delete(id: Long) {
        memoRep.delete(id)
    }
}