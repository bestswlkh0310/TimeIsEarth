package com.example.timeisearth.viewModel
import androidx.lifecycle.ViewModel
import com.example.timeisearth.model.Todo

class MainViewModel: ViewModel() {
    val todoList = arrayListOf(
        Todo("밥먹기", "밥을 먹을거임", "3"),
        Todo("밥먹기", "밥을 먹을거임", "3"),
        Todo("밥먹기", "밥을 먹을거임", "3"),
        Todo("밥먹기", "밥을 먹을거임", "3"),
    )
}