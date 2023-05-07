package com.example.timeisearth.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.timeisearth.databinding.FragmentTodoBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG
import com.example.timeisearth.view.activity.main.TodoEditClickListener
import com.example.timeisearth.view.activity.main.TodoItemClickListener
import com.example.timeisearth.viewModel.MainViewModel
import com.example.timeisearth.viewModel.TodoContentViewModel


class TodoFragment : Fragment() {
    private val viewModel: TodoContentViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentTodoBinding
    private lateinit var todoItemClickListener: TodoEditClickListener
    private lateinit var todo: Todo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "TodoFragment - onCreateView() called")
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        initOnClickListener()
        initTodoItem()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        todoItemClickListener = context as TodoEditClickListener
    }
    private fun initTodoItem() {
        todo = activityViewModel.todo!!
        with(binding) {
            title.setText(todo.title)
            content.setText(todo.content)
        }
    }

    private fun initOnClickListener() {
        with(binding) {
            btnComplete.setOnClickListener {
                todo.title = title.text.toString()
                todo.content = content.text.toString()
                activityViewModel.updateTodo(todo)
                todoItemClickListener.onEditSaveClick()
                exit()
            }

            btnBack.setOnClickListener {
                Log.d(TAG, "TodoFragment - initOnClickListener() called")
                exit()
            }
        }
    }

    private fun exit() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this@TodoFragment)
            ?.commit()
    }
}