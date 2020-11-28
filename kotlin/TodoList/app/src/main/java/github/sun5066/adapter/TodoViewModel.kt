package github.sun5066.adapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import github.sun5066.model.TodoVO
import github.sun5066.persistence.TodoRepository

class TodoViewModel(app: Application) : AndroidViewModel(app) {
    private val todoRepository = TodoRepository(app)

    fun selectAll(): LiveData<MutableList<TodoVO>> {
        return todoRepository.selectAll()
    }

    fun findById(id: Long): TodoVO {
        return todoRepository.findById(id)
    }

    fun save(todoVO: TodoVO) {
        todoRepository.save(todoVO)
    }

    fun delete(id: Long) {
        todoRepository.delete(id)
    }
}