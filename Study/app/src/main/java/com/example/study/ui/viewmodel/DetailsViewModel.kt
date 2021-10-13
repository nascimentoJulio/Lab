package com.example.study.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.models.TaskModel
import com.example.study.repository.TaskDb

class DetailsViewModel(context: Context) : ViewModel(){
    private var db: TaskDb = TaskDb.getInstance(context)
    private var mRepository = db.taksDAO

    private val mTask: MutableLiveData<TaskModel> = MutableLiveData()
    private var task: LiveData<TaskModel> = this.mTask

    fun getTaskById(id: Long): LiveData<TaskModel>{
        this.mTask.value = this.mRepository.getTaskById(id)
        return task
    }

    fun delete(taskModel: TaskModel){
        this.mRepository.delete(taskModel)
    }

}