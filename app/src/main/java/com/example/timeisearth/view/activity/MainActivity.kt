package com.example.timeisearth.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timeisearth.TodoAdapter
import com.example.timeisearth.util.TodoManager
import com.example.timeisearth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initTodoRecyclerView()
    }

    private fun initTodoRecyclerView() {
        val todoList = TodoManager.todoList
        binding.rvTodoList.adapter = TodoAdapter(todoList)
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)
    }
}