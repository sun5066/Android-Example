package github.sun5066.todov1.adapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import github.sun5066.todov1.db.TodoRepository
import github.sun5066.todov1.model.TodoVO

class TodoViewModel(app: Application) : AndroidViewModel(app) {
    private var repository = TodoRepository(app)

    fun selectAll(): LiveData<MutableList<TodoVO>> = repository.selectAll()

    fun findById(id: Long): TodoVO = repository.findById(id)

    fun save(todoVO: TodoVO) = repository.save(todoVO)

    fun update(todoVO: TodoVO) = repository.save(todoVO)

    fun delete(id: Long) = repository.delete(id)
}