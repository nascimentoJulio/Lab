package com.example.study.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.models.TaskModel
import com.example.study.repository.TaskDb

class ResultSearchViewModel(context: Context) : ViewModel() {

    private var db: TaskDb = TaskDb.getInstance(context)
    private var mRepository = db.taksDAO

    private val mTasks: MutableLiveData<MutableList<TaskModel>> = MutableLiveData()
    private var tasks: LiveData<MutableList<TaskModel>> = this.mTasks

    fun getTasksByName(title: String): LiveData<MutableList<TaskModel>> {
        this.mTasks.value = this.mRepository.getTasksByTitle(title)
        return this.tasks
    }

    fun delete(taskModel: TaskModel){
        this.mRepository.delete(taskModel)
    }
}