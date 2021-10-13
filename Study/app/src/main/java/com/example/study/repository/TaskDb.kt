package com.example.study.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.study.models.SearchModel

import com.example.study.models.TaskModel

@Database(entities = [TaskModel::class, SearchModel::class],version = 1, exportSchema = false)
abstract class TaskDb : RoomDatabase() {
    abstract val taksDAO : TaskDAO

    companion object{
        private var INSTANCE : TaskDb? = null

        fun getInstance(context: Context) : TaskDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room
                        .databaseBuilder(context, TaskDb::class.java,"tasks")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}