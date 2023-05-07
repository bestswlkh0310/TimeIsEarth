package com.example.timeisearth.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.core.view.KeyEventDispatcher.Component
import androidx.recyclerview.widget.RecyclerView
import com.example.timeisearth.databinding.TodoItemBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG
import com.example.timeisearth.view.activity.main.TodoItemClickListener

class TodoAdapter(
    private val todoList: MutableList<Todo>,
    private val itemClickListener: TodoItemClickListener
    ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(inflater, parent, false)
//        todoList.forEach {
//            Log.d(TAG, "# ${it.title} - onBindViewHolder() called")
//        }
        return TodoViewHolder(binding)
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.setTodoTitle(todo.title!!)
        holder.setTodoDeadline(todo.deadline!!)
        holder.initItemClickListener(todo, position)
    }
    inner class TodoViewHolder(private val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setTodoTitle(title: String) {
            binding.checkbox.text = title
        }
        fun setTodoDeadline(deadline: String) {
            binding.deadline.text = deadline
        }
        fun initItemClickListener(todo: Todo, position: Int) {
            binding.checkbox.setOnCheckedChangeListener {view, isChecked ->
                itemClickListener.onTodoItemCheckedChanged(view, todo, isChecked)
            }
            binding.btnDelete.setOnClickListener {itemClickListener.onTodoDeleteClick(todo, position) }
            binding.btnEdit.setOnClickListener { itemClickListener.onTodoEditClick(todo, position) }
        }
    }
}