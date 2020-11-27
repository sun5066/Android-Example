package com.biz.hello_03.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.biz.hello_03.model.MemoVO

@Dao
interface MemoDao {
    // LiveData : Spring JPA 의 Optional 역할
    // null 연산 에러 방지
    @Query("SELECT * FROM tbl_memo")
    open fun selectAll(): LiveData<MutableList<MemoVO>>

    @Query("SELECT * FROM tbl_memo WHERE id= :id")
    open fun findById(id: Long): MemoVO

    // OnConflictStrategy.REPLACE
    // 새로운 데이터라면 Insert 수행하고
    // 아니라면 Update 를 수행함.
    // 그래서 Update 메서드가 따로 필요없음
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun save(memoVO: MemoVO)

    @Update
    open fun update(memoVO: MemoVO)

    @Query("DELETE FROM tbl_memo WHERE id = :id")
    open fun delete(id: Long)
}