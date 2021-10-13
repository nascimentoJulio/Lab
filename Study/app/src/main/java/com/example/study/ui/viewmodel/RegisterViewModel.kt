package com.example.study.ui.viewmodel

import android.content.Context

import androidx.lifecycle.ViewModel
import com.example.study.models.TaskModel
import com.example.study.repository.TaskDb

class RegisterViewModel(context: Context) : ViewModel() {
    private var db: TaskDb = TaskDb.getInstance(context)
    private var mRepository = db.taksDAO


    fun insert(taskModel: TaskModel) {
        this.mRepository.insert(taskModel)
    }
}