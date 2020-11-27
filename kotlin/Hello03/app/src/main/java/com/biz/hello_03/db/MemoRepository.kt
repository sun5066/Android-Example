package com.biz.hello_03.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.biz.hello_03.model.MemoVO

// 어플리케이션 전체에서
// 데이터베이스를 한번만 생성하기
class MemoRepository(app: Application) {
    private lateinit var memoDao: MemoDao

    init {
        // db 객체가 null 인경우
        // table 생성
        var db: MemoDataBase? = MemoDataBase.getInstance(app)
        if (db != null) {
            memoDao = db.getMemoDao()!!
        }
    }

    fun selectAll(): LiveData<MutableList<MemoVO>> {
        return memoDao.selectAll()
    }

    fun findById(id: Long): MemoVO {
        return memoDao.findById(id)
    }

    fun save(memoVO: MemoVO) {
        // MemoDatabase 에 선언된 databaseWriterExcutor 메서드를
        // Thread 방식으로 호출하여 Insert 를 수행하라
        MemoDataBase.databaseWriterExecutor.execute(Runnable { memoDao.save(memoVO) })
    }

    fun delete(memoVO: MemoVO) {
        memoDao.delete(memoVO.id)
    }

    fun delete(id: Long) {
        memoDao.delete(id)
    }
}