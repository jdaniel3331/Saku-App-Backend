package com.jdaniel.sakuappapi.task.service

import com.jdaniel.sakuappapi.task.model.dto.CreateTaskDto
import com.jdaniel.sakuappapi.task.model.dto.TaskDto

interface TaskService {

    fun createTask(task: CreateTaskDto): String
    fun getAllTasks(userId: Long): List<TaskDto>
    fun deleteTask(taskId: Long): String
    fun changeTaskTitle(taskId: Long, newTitle: String): String
    fun getTaskById(taskId: Long): TaskDto
    fun changeTaskState(taskId: Long, taskStateId: Short): String
    fun changeTaskPriorityLevel(taskId: Long, priorityLevelId: Short): String

}
