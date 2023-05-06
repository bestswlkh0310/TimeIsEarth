package com.example.timeisearth.viewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timeisearth.model.db.TodoDatabase
import com.example.timeisearth.model.entity.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val todoList: MutableList<Todo> = arrayListOf()
    private val db = TodoDatabase.getInstace(application.applicationContext)!!
    private val todoDAO = db.todoDAO()

    fun insertTodo(todo: Todo) {
        todoList.add(todo)
        CoroutineScope(Dispatchers.IO).launch {
            todoDAO.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        todoList.remove(todo)
        viewModelScope.launch {
            todoDAO.deleteTodo(todo)
        }
    }

    fun updateTodo(position: Int, todo: Todo) {
        todoList[position] = todo
        viewModelScope.launch {
            todoDAO.updateTodo(todo)
        }
    }

    fun allTodos(): List<Todo> {
        return todoDAO.allTodos()
    }
}