package com.jdaniel.sakuappapi.task.service

import com.jdaniel.sakuappapi.task.model.dto.CreateTaskDto
import com.jdaniel.sakuappapi.task.model.dto.TaskDto

interface TaskService {

    fun createTask(task: CreateTaskDto): String
    fun getAllTasks(userId: Long): List<TaskDto>

}
