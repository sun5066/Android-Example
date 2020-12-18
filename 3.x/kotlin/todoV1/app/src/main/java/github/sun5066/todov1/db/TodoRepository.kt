package github.sun5066.todov1.db

import android.app.Application
import androidx.lifecycle.LiveData
import github.sun5066.todov1.model.TodoVO

class TodoRepository(app: Application) {
    private lateinit var todoDao: TodoDao

    // init
    // 뭐 constructor 안에 값 넣어서 해도 되겠지만 여기까진 잘 모르겠고 아직은
    // 값을 받아오는게 아니니까..
    // init 에서 dao 를 초기화 해주자
    init {
        var db: TodoDataBase? = TodoDataBase.getInstance(app)
        if (db != null) {
            todoDao = db.getTodoDao()!!
        }
    }

    fun selectAll(): LiveData<MutableList<TodoVO>> = todoDao.selectAll()

    fun findById(id: Long): TodoVO = todoDao.findById(id)

    fun save(todoVO: TodoVO) = todoDao.save(todoVO)

    fun update(todoVO: TodoVO) = todoDao.save(todoVO)

    fun delete(todoVO: TodoVO) = todoDao.delete(todoVO.id)

    fun delete(id: Long) = todoDao.delete(id)
}