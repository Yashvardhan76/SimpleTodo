package com.justlime.simpletodo.data.source

import com.justlime.simpletodo.data.dao.TaskDao
import com.justlime.simpletodo.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskLocalDataSource @Inject constructor(private val taskDao: TaskDao) {
    fun getTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

}