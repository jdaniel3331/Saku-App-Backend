package com.jdaniel.sakuappapi.task.controller

import com.jdaniel.sakuappapi.common.response.ApiResponse
import com.jdaniel.sakuappapi.task.model.dto.TaskDto
import com.jdaniel.sakuappapi.task.service.TaskService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController {
    @Autowired
    private val taskService: TaskService? = null

    @PostMapping
    public fun createTask(@RequestBody @Valid task: TaskDto): ResponseEntity<ApiResponse> {
        var response: ApiResponse? = ApiResponse(HttpStatus.CREATED.name,taskService?.createTask(task), HttpStatus.CREATED.value())
        return ResponseEntity<ApiResponse>(response, HttpStatus.CREATED)
    }
}