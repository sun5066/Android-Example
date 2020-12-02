package github.sun5066.persistence

import android.app.Application
import androidx.lifecycle.LiveData
import github.sun5066.model.TodoVO

class TodoRepository(app: Application) {
    private lateinit var todoDao: TodoDao

    init {
        var db: TodoDataBase? = TodoDataBase.getInstance(app)
        if (db != null) {
            todoDao = db.getTodoDao()!!
        }
    }

    fun selectAll(): LiveData<MutableList<TodoVO>> {
        return todoDao.selectAll()
    }

    fun findById(id: Long): TodoVO {
        return todoDao.findById(id)
    }

    fun save(todoVO: TodoVO) {
//        todoDao.save(todoVO)
        TodoDataBase.databaseWriterExecutor.execute(Runnable { todoDao.save(todoVO) })
    }

    fun delete(id: Long) {
        todoDao.delete(id)
    }
}