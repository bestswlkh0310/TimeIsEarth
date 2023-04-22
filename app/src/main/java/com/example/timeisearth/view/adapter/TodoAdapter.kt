package com.example.timeisearth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timeisearth.network.model.Todo

class TodoAdapter(private val todoList: List<Todo>): RecyclerView.Adapter<TodoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo, parent, false))
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todo = todoList[position]
        holder.title.text = todo.title
        holder.deadline.text = todo.deadline
    }
}

class TodoHolder(v: View): RecyclerView.ViewHolder(v) {
//    val binding: TodoBinding by lazy { TodoBinding.inflate(LayoutInflater.from(v.context)) }
    val title = v.findViewById<CheckBox>(R.id.cb_title)
    val deadline = v.findViewById<TextView>(R.id.tv_deadline)
}