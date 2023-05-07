package com.example.timeisearth.view.activity.main

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timeisearth.R
import com.example.timeisearth.view.adapter.TodoAdapter
import com.example.timeisearth.databinding.ActivityMainBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG
import com.example.timeisearth.view.fragment.TodoFragment
import com.example.timeisearth.viewModel.MainViewModel
import com.example.timeisearth.viewModel.TodoItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), TodoDialogClickListener, TodoItemClickListener, TodoEditClickListener {
    private val dialog: TodoDialog by lazy { TodoDialog(this, this) }
    private val viewModel: MainViewModel by viewModels()
    private val todoItemViewModel: TodoItemViewModel by viewModels()
    private lateinit var adapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        Log.d(TAG, "MainActivity - onCreate() called")

        initTodoList()
        initToolBar()
        binding.fabAddTodo.setOnClickListener { showDialog() }
    }

    private fun initTodoList() {
        adapter = TodoAdapter(viewModel.todoList, this)

        with(binding) {
            rvTodoList.adapter = adapter
            rvTodoList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTodoList.addItemDecoration(
                DividerItemDecoration(
                    applicationContext,
                    LinearLayout.VERTICAL
                )
            )
        }
        viewModel.initTodoList()
        adapter.notifyItemRangeInserted(0, viewModel.todoList.size)
    }

    private fun showDialog() {
        dialog.show()
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolBar)
        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_menu_24)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun changeDarkMode() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mode -> {
                changeDarkMode()
                true
            }

            android.R.id.home -> {
                Log.d(TAG, "MainActivity - onOptionsItemSelected() called")
                binding.drawer.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar, menu)
        return true
    }

    override fun onDialogSaveClick(todo: Todo) {
        viewModel.insertTodo(todo)
        adapter.notifyItemInserted(viewModel.todoList.size - 1)
    }

    override fun onTodoItemCheckedChanged(view: CompoundButton, todo: Todo, isChecked: Boolean) {
        Log.d(TAG, "title - ${todo.title} - check - $isChecked onTodoItemCheckedChanged() called")

        if (isChecked) {
            view.setBackgroundColor(0xFFCCCCCC.toInt())
            view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            view.setBackgroundColor(Color.WHITE)
            view.paintFlags = 1283
        }
    }

    override fun onTodoDeleteClick(todo: Todo, position: Int) {
        Log.d(TAG, "${todo.id} - ${todo.title} : ${todo.content} [$position] - onTodoDeleteClick() called")
        viewModel.deleteTodo(todo, position)
        adapter.notifyDataSetChanged()
    }

    override fun onTodoEditClick(todo: Todo, position: Int) {
        viewModel.todo = todo
        Log.d(TAG, "title - ${todo.title} content - ${todo.content} - onItemClick() called")
        viewModel.editedTodoPosition = position
        replaceFragment(TodoFragment())
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, TodoFragment())
//            .commitNow()
    }

    private fun <T> replaceFragment(fragment: T) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment as Fragment)
            .commitNow()
    }

    override fun onEditSaveClick() {
        if (viewModel.editedTodoPosition != null) {
            adapter.notifyItemChanged(viewModel.editedTodoPosition!!)
        } else {
            Log.d(TAG, "MainActivity - onEditSaveClick() called")
        }
    }
}