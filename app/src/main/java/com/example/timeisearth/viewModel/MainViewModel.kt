package com.example.timeisearth.viewModel
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.timeisearth.model.db.TodoDatabase
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class)
class MainViewModel(application: Application): AndroidViewModel(application) {
    val todoList: MutableList<Todo> = arrayListOf()
    private val db = TodoDatabase.getInstance(application.applicationContext)!!
    private val todoDAO = db.todoDAO()

    var todo: Todo? = null
    fun insertTodo(todo: Todo) {
        todoList.add(todo)
        GlobalScope.launch {
            todoDAO.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        todoList.remove(todo)
        GlobalScope.launch {
            todoDAO.deleteTodo(todo)
        }
    }

    fun updateTodo(position: Int, todo: Todo) {
        todoList[position] = todo
        GlobalScope.launch {
            todoDAO.updateTodo(todo)
        }
    }

    fun initTodoList() {
        Log.d(TAG, "MainViewModel - initTodoList() called")
        GlobalScope.launch {
                todoDAO.allTodos().forEach {
                    todo ->
                    todoList.add(todo)
                }
            }
    }
}
