package com.justlime.simpletodo.data.source

import com.justlime.simpletodo.data.dao.TaskDao
import com.justlime.simpletodo.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 *  TaskLocalDataSource.kt know as the single source of truth for the data
 * it means that it is responsible for Curd operation if data from the database or network using api.
 * It also update the viewModel with the data from the database.
 * - It manages the data and also it can handle exception
 * - It is a optional Layer but Recommened
 * **/
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
    suspend fun deleteTask(taskId: Int) {
        taskDao.delete(taskId)
    }


}