package github.sun5066.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.R
import github.sun5066.model.TodoVO

class TodoViewAdapter(
    var todoList: MutableList<TodoVO>,
    var onDelete: (Any) -> Unit,
    var onUpdate: (Any) -> Unit,
    var onComplete: (Any) -> Unit
) : RecyclerView.Adapter<TodoViewAdapter.TodoHolder?>() {

    fun setList(todoList: MutableList<TodoVO>) {
        this.todoList = todoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoHolder(view)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.txtDate.text = todoList[position]?.date.toString()
        holder.txtTime.text = todoList[position]?.time.toString()
        holder.todo.text = todoList[position]?.todo.toString()

        when (todoList[position].isComplete) {
            true -> {
                holder.checkComplete.text = "✓"
                holder.todo.paintFlags = holder.todo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else -> {
                holder.checkComplete.text = "○"
                holder.todo.paintFlags = 0
            }
        }

        val id: Long = todoList[position].id.toLong()
        holder.btnDelete.setOnClickListener(View.OnClickListener { onDelete(id) })
        holder.todo.setOnClickListener(View.OnClickListener { onUpdate(id) })
        holder.checkComplete.setOnClickListener(View.OnClickListener { onComplete(id) })
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtDate: TextView = itemView.findViewById(R.id.txt_date)
        var txtTime: TextView = itemView.findViewById(R.id.txt_time)
        var todo: TextView = itemView.findViewById(R.id.txt_todo_item)
        val btnDelete: Button = itemView.findViewById(R.id.btn_todo_item_delete)
        val checkComplete: TextView = itemView.findViewById(R.id.checkComplete)
    }
}