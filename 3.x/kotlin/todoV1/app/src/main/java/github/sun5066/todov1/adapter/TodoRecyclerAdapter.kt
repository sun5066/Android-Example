package github.sun5066.todov1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.todov1.R
import github.sun5066.todov1.model.TodoVO

class TodoRecyclerAdapter(private var todoList: MutableList<TodoVO>) :
    RecyclerView.Adapter<TodoRecyclerAdapter.TodoHolder?>() {

    fun setList(todoList: MutableList<TodoVO>) {
        this.todoList = todoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoHolder(view)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.txtDate.text = todoList[position].date
        holder.txtTime.text = todoList[position].time
        holder.txtTodo.text = todoList[position].todo
    }

    override fun getItemCount(): Int = todoList.size

    class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtDate = itemView.findViewById<TextView>(R.id.txtDate)
        var txtTime = itemView.findViewById<TextView>(R.id.txtTime)
        var txtTodo = itemView.findViewById<TextView>(R.id.txtTodo)
    }
}