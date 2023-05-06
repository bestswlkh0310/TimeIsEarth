package com.example.timeisearth.view.activity.main

import com.example.timeisearth.model.entity.Todo


interface TodoListener {
    fun notifyNewTodo(todo: Todo)
}