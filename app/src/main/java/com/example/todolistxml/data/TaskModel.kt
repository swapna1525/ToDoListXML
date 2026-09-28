package com.example.todolistxml.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskModel(
    val title: String,

    var isCompleted: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)