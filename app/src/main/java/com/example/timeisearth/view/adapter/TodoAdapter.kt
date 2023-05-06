package com.example.timeisearth.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timeisearth.R
import com.example.timeisearth.databinding.TodoBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG

class TodoAdapter(
    private val todoList: MutableList<Todo>,
    ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private lateinit var binding: TodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = TodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo, parent, false))
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.setTodoTitle(todo.title!!)
        holder.setTodoDeadline(todo.deadline!!)
    }
    inner class TodoViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun setTodoTitle(title: String) {
            Log.d(TAG, "$title - adapter.setTodoTitle() called")
            binding.cbTitle.text = title
        }
        fun setTodoDeadline(deadline: String) {
            Log.d(TAG, "$deadline - adapter.setTodoTitle() called")
            binding.tvDeadline.text = "오류수정!!"
        }
    }
}