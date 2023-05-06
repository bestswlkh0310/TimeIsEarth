package com.example.timeisearth.view.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timeisearth.R
import com.example.timeisearth.view.adapter.TodoAdapter
import com.example.timeisearth.databinding.ActivityMainBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG
import com.example.timeisearth.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), TodoListener {
    private val dialog: TodoDialog by lazy { TodoDialog(this, this) }
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding
    private var isDrawerOpen = false

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
        adapter = TodoAdapter(viewModel.todoList)

        with(binding) {
            rvTodoList.adapter = adapter
            rvTodoList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTodoList.setOnClickListener {

            }
        }
        viewModel.initTodoList()
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
    override fun notifyNewTodo(todo: Todo) {
        viewModel.insertTodo(todo)
        adapter.notifyItemInserted(viewModel.todoList.size - 1)
    }
}