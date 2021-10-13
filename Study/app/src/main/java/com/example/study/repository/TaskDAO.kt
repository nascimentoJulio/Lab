package com.example.study.repository

import androidx.room.*
import com.example.study.models.SearchModel
import com.example.study.models.TaskModel

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TaskModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(searchModel: SearchModel)

    @Update
    fun update(task: TaskModel)

    @Delete
    fun delete(task: TaskModel)

    @Delete
    fun delete(searchModel: SearchModel)

    @Query("SELECT * FROM tasks ORDER BY title")
    fun getAllTasks() : MutableList<TaskModel>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Long) : TaskModel

    @Query("SELECT * FROM tasks WHERE title LIKE :title")
    fun getTasksByTitle(title: String) : MutableList<TaskModel>

    @Query("SELECT * FROM searches ORDER BY date DESC LIMIT 10")
    fun getLastSearches(): MutableList<SearchModel>

}