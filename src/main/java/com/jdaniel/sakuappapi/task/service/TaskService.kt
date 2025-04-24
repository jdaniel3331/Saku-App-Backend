package com.jdaniel.sakuappapi.task.service

import com.jdaniel.sakuappapi.task.model.Task

interface TaskService {

    fun createTask(task: Task): Task
}