package com.jdaniel.sakuappapi.task.controller

import com.jdaniel.sakuappapi.common.response.ApiResponse
import com.jdaniel.sakuappapi.task.model.Task
import com.jdaniel.sakuappapi.task.model.dto.CreateTaskDto
import com.jdaniel.sakuappapi.task.model.dto.TaskDto
import com.jdaniel.sakuappapi.task.service.TaskService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController {
    @Autowired
    private val taskService: TaskService? = null

    @PostMapping
    public fun createTask(@RequestBody @Valid task: CreateTaskDto): ResponseEntity<ApiResponse<String>> {
        val message = taskService?.createTask(task)
        val response = ApiResponse<String>(
            HttpStatus.CREATED.name,
            message,
            HttpStatus.CREATED.value()
        )
        return ResponseEntity(response, HttpStatus.CREATED)
    }
    @GetMapping
    public fun getAllTasks(@RequestParam("userId") userId: Long): ResponseEntity<ApiResponse<List<TaskDto>>> {
        val tasks = taskService?.getAllTasks(userId)
        val response = ApiResponse<List<TaskDto>>(
            HttpStatus.OK.name,
            "Tasks retrieved successfully",
            HttpStatus.OK.value(),
            tasks
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
    @DeleteMapping
    public fun deleteTask(@RequestParam("taskId") taskId: Long): ResponseEntity<ApiResponse<String>> {

        val response = ApiResponse<String>(
            HttpStatus.OK.name,
            taskService?.deteleTask(taskId),
            HttpStatus.OK.value()
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}