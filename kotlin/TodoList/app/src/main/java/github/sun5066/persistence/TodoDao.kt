package github.sun5066.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.sun5066.model.TodoVO

@Dao
interface TodoDao {

    @Query("SELECT * FROM tbl_todo")
    open fun selectAll(): LiveData<MutableList<TodoVO>>

    @Query("SELECT * FROM tbl_todo WHERE id= :id")
    open fun findById(id: Long): TodoVO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun save(todoVO: TodoVO)

    @Query("DELETE FROM tbl_todo WHERE id= :id")
    open fun delete(id: Long)
}