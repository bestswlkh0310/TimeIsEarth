package com.example.timeisearth.view.fragment

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
import com.example.timeisearth.viewModel.MainViewModel
import com.example.timeisearth.viewModel.TodoContentViewModel


class TodoFragment : Fragment() {
    private val viewModel: TodoContentViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentTodoBinding
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
    private fun initTodoItem() {
        todo = activityViewModel.todo!!
        with(binding) {
            title.text = todo.title
            content.text = todo.content
        }
    }
    private fun initOnClickListener() {
        with(binding) {
            btnComplete.setOnClickListener {

            }

            btnEdit.setOnClickListener {

            }

            btnDelete.setOnClickListener {

            }

            btnBack.setOnClickListener {
                Log.d(TAG, "TodoFragment - initOnClickListener() called")
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this@TodoFragment)
                    ?.commit()
            }
        }
    }
}