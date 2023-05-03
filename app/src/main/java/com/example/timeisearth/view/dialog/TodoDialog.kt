package com.example.timeisearth.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.timeisearth.R
import com.example.timeisearth.databinding.TodoDialogBinding
import com.example.timeisearth.model.Todo
import com.example.timeisearth.viewModel.MainViewModel

class TodoDialog(context: Context): Dialog(context), View.OnClickListener {
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

    fun onClickSave() {
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
            dismiss()
        }
    }

    fun onClickCancel() {
        dismiss()
    }
}