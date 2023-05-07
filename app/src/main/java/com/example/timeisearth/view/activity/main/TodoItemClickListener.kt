package com.example.timeisearth.view.activity.main

import android.widget.CompoundButton
import com.example.timeisearth.model.entity.Todo

interface TodoItemClickListener {
    fun onTodoItemCheckedChanged(view: CompoundButton, todo: Todo, isChecked: Boolean)
    fun onTodoDeleteClick(todo: Todo, position: Int)
    fun onTodoEditClick(todo: Todo)
}