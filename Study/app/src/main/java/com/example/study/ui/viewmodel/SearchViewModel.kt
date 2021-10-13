package com.example.study.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.study.models.SearchModel
import com.example.study.repository.TaskDb

class SearchViewModel(context: Context) : ViewModel() {

    private var db: TaskDb = TaskDb.getInstance(context)
    private var mRepository = db.taksDAO

    private val mTasks: MutableLiveData<MutableList<SearchModel>> = MutableLiveData()
    private var tasks: LiveData<MutableList<SearchModel>> = this.mTasks

    fun getSearches(): LiveData<MutableList<SearchModel>> {
        this.mTasks.value = this.mRepository.getLastSearches()
        return this.tasks
    }

    fun insert(searchModel: SearchModel){
        this.mRepository.insert(searchModel)
    }

    fun delete(searchModel: SearchModel) {
        this.mRepository.delete(searchModel)
    }
}