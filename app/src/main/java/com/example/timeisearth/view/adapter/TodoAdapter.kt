package com.example.timeisearth.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timeisearth.databinding.TodoItemBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.view.activity.main.TodoItemClickListener

class TodoAdapter(
    private val todoList: MutableList<Todo>,
    private val itemClickListener: TodoItemClickListener
    ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.setTodoTitle(todo.title!!)
        holder.setTodoDeadline(todo.deadline!!)
        holder.initItemClickListener(todo)
    }
    inner class TodoViewHolder(private val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setTodoTitle(title: String) {
            binding.cbTitle.text = title
        }
        fun setTodoDeadline(deadline: String) {
            binding.tvDeadline.text = deadline
        }
        fun initItemClickListener(todo: Todo) {
            binding.todoItem.setOnClickListener { itemClickListener.onItemClick(todo) }
        }
    }
}