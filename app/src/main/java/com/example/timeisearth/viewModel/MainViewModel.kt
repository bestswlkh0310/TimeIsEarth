package com.example.timeisearth.viewModel
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.timeisearth.model.db.TodoDatabase
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.model.repository.TodoRepository
import com.example.timeisearth.util.constant.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val todoList: MutableList<Todo> = arrayListOf()
    private val db = TodoDatabase.getInstance(application.applicationContext)!!
    val todoDAO = db.todoDAO()
    private val todoRepository = TodoRepository(todoDAO)

    var todo: Todo? = null
    fun insertTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todo.id = todoRepository.insertTodo(todo)
            todoList.add(todo)
        }
    }

    fun deleteTodo(todo: Todo, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
        todoList.removeAt(position)
    }

    fun updateTodo(position: Int, todo: Todo) {
        todoList[position] = todo
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    fun initTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getAllTodo().forEach {
                todoList.add(it)
            }
        }
    }
}