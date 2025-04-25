package com.jdaniel.sakuappapi.task.service

import com.jdaniel.sakuappapi.task.model.Task
import com.jdaniel.sakuappapi.task.model.dto.TaskDto

interface TaskService {

    fun createTask(task: TaskDto): String
}