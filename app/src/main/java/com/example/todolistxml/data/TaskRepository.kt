package com.example.todolistxml.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insertTask(task: TaskModel) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskModel) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskModel) {
        taskDao.deleteTask(task)
    }

    fun getAllTasks(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks()
    }
}