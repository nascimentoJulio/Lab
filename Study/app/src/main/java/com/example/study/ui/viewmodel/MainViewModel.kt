package com.example.study.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.models.TaskModel
import com.example.study.repository.TaskDb

class MainViewModel(context: Context) : ViewModel() {

    private var db: TaskDb = TaskDb.getInstance(context)
    private var mRepository = db.taksDAO

    private val mAllTasks: MutableLiveData<MutableList<TaskModel>> = MutableLiveData()
    private var allTasks: LiveData<MutableList<TaskModel>> = this.mAllTasks

    fun getAllTasks(): LiveData<MutableList<TaskModel>> {
        this.mAllTasks.value = this.mRepository.getAllTasks()
        return this.allTasks
    }

    fun delete(task: TaskModel){
        this.mRepository.delete(task)
    }

}