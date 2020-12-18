package github.sun5066.todov1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import github.sun5066.todov1.model.TodoVO
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [TodoVO::class], version = 1, exportSchema = false)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao?

    companion object {
        private var INSTANCE: TodoDataBase? = null
        private val NUMBER_THREAD = 5;
        public val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(
            NUMBER_THREAD
        )

        @Synchronized
        fun getInstance(context: Context): TodoDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDataBase::class.java,
                    "todo_database"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}