package com.example.timeisearth.model.repository

import com.example.timeisearth.model.dao.TodoDAO
import com.example.timeisearth.model.entity.Todo

class TodoRepository(private val todoDAO: TodoDAO) {
    fun insertTodo(todo: Todo): Long {
        return todoDAO.insertTodo(todo)
    }

    fun deleteTodo(todo: Todo) {
        todoDAO.deleteTodo(todo)
    }

    fun updateTodo(todo: Todo) {
        todoDAO.updateTodo(todo)
    }

    fun getAllTodo(): List<Todo> {
        return todoDAO.getAllTodo()
    }
}