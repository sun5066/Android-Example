package github.sun5066.todov1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_todo")
data class TodoVO(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "todo")
    var todo: String = "",

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "time")
    var time: String = ""
)