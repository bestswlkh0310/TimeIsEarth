package com.example.timeisearth.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.timeisearth.R
import com.example.timeisearth.databinding.TodoBinding
import com.example.timeisearth.viewModel.TodoViewModel

class TodoFragment : Fragment() {
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var binding: TodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TodoBinding.inflate(inflater, container, false)
        return binding.root
    }
}