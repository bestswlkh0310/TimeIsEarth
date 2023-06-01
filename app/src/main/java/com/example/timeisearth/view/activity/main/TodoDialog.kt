package com.example.timeisearth.view.activity.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.timeisearth.R
import com.example.timeisearth.databinding.TodoDialogBinding
import com.example.timeisearth.model.entity.Todo
import com.example.timeisearth.util.constant.TAG

class TodoDialog(context: Context, private val todoListener: TodoDialogClickListener): Dialog(context), View.OnClickListener {
    private lateinit var binding: TodoDialogBinding
    private var title = ""
    private var content = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnSave.setOnClickListener(this@TodoDialog)
            btnCancel.setOnClickListener(this@TodoDialog)
        }
        setCancelable(false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> onClickSave()
            R.id.btn_cancel -> onClickCancel()
        }
    }

    private fun onClickSave() {
        title = binding.tvTitle.text.toString()
        content = binding.tvContent.text.toString()
        if (title == "") {
            Toast.makeText(context, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
        } else if (content == "") {
            Toast.makeText(context, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
        } else {
            val todo = Todo(
                title = title,
                content = content,
                deadline = "123"
            )
            Log.d(TAG, "newTitle - $title newContent - $content - onClickSave() called")
            with(binding) {
                tvTitle.setText("")
                tvContent.setText("")
            }
            todoListener.onDialogSaveClick(todo)
            dismiss()
        }
    }

    fun onClickCancel() {
        dismiss()
    }

}

interface TodoDialogClickListener {
    fun onDialogSaveClick(todo: Todo)
}