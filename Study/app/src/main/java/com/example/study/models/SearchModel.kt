package com.example.study.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searches")
data class SearchModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val date: Long
)
