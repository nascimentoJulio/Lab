package com.example.study.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "due_date")
    var dueDate: String,

    @ColumnInfo(name = "topic")
    var topic: String,

    @ColumnInfo(name = "is_completed")
    var isCompleted: Boolean
)