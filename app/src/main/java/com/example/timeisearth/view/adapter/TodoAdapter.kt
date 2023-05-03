package com.example.timeisearth.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timeisearth.R
import com.example.timeisearth.databinding.ActivityMainBinding
import com.example.timeisearth.databinding.TodoBinding
import com.example.timeisearth.model.Todo

class TodoAdapter(
    private val todoList: List<Todo>,
    ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private lateinit var binding: TodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = TodoBinding.inflate(
            inflater,
            parent,
            false
        )

        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo, parent, false))
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.title.text = todo.title
        holder.deadline.text = todo.deadline
    }
    inner class TodoViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val title: CheckBox = binding.cbTitle
        val deadline: TextView = binding.tvDeadline
    }
}