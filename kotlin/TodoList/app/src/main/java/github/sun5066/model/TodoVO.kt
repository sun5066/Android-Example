package github.sun5066.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_todos")
class TodoVO {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public var id: Long = 0

    @ColumnInfo(name = "date")
    public var date: String = ""

    @ColumnInfo(name = "time")
    public var time: String = ""

    @ColumnInfo(name = "todo")
    public var todo: String = ""

    @ColumnInfo(name = "isComplete")
    public var isComplete: Boolean = false
}