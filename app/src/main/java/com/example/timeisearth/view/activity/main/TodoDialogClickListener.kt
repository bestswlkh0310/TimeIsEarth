package com.example.timeisearth.view.activity.main

import com.example.timeisearth.model.entity.Todo


interface TodoDialogClickListener {
    fun notifyNewTodo(todo: Todo)
}