package com.example.timeisearth.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timeisearth.R
import com.example.timeisearth.view.adapter.TodoAdapter
import com.example.timeisearth.databinding.ActivityMainBinding
import com.example.timeisearth.view.dialog.TodoDialog
import com.example.timeisearth.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val dialog: TodoDialog by lazy { TodoDialog(this) }
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        initTodoRecyclerView()
        binding.fabAddTodo.setOnClickListener { showDialog() }
    }

    private fun initTodoRecyclerView() {
        val todoList = viewModel.todoList
        val adapter = TodoAdapter(todoList)
        with(binding) {
            rvTodoList.adapter = adapter
            rvTodoList.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    fun showDialog() {
        dialog.show()
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show()
    }
}