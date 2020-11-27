package com.biz.hello_03.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.biz.hello_03.model.MemoVO
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [MemoVO::class], version = 1, exportSchema = false)
abstract class MemoDataBase : RoomDatabase() {
    abstract fun getMemoDao(): MemoDao?

    // static 메서드
    companion object {
        private var INSTANCE: MemoDataBase? = null
        private val NUMBER_THREADS = 5;
        public val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(
            NUMBER_THREADS
        )

        @Synchronized
        fun getInstance(context: Context): MemoDataBase? {
            if (INSTANCE == null) {
                // Instance 생성, DB 객체생성

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDataBase::class.java,
                    "memo_database"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}