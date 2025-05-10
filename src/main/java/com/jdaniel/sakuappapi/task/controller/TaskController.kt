package com.jdaniel.sakuappapi.task.controller

import com.jdaniel.sakuappapi.common.response.ApiResponse
import com.jdaniel.sakuappapi.task.model.dto.ChangeTitleDto
import com.jdaniel.sakuappapi.task.model.dto.CreateTaskDto
import com.jdaniel.sakuappapi.task.model.dto.TaskDto
import com.jdaniel.sakuappapi.task.service.TaskService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun createTask(@RequestBody @Valid task: CreateTaskDto): ResponseEntity<ApiResponse<String>> {
        val message = taskService?.createTask(task)
        val response = ApiResponse<String>(
            HttpStatus.CREATED.name,
            message,
            HttpStatus.CREATED.value()
        )
        return ResponseEntity(response, HttpStatus.CREATED)
    }
    @GetMapping
    fun getAllTasks(@RequestParam("userId") userId: Long): ResponseEntity<ApiResponse<List<TaskDto>>> {
        val tasks = taskService?.getAllTasks(userId)
        val response = ApiResponse<List<TaskDto>>(
            HttpStatus.OK.name,
            "Tasks retrieved successfully",
            HttpStatus.OK.value(),
            tasks
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: Long): ResponseEntity<ApiResponse<String>> {

        val response = ApiResponse<String>(
            HttpStatus.OK.name,
            taskService?.deleteTask(taskId),
            HttpStatus.OK.value()
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
    @PatchMapping
    fun changeTaskTitle(@RequestBody @Valid changeTitleDto: ChangeTitleDto): ResponseEntity<ApiResponse<String>> {
        val response = ApiResponse<String>(
            HttpStatus.OK.name,
            taskService?.changeTaskTitle(changeTitleDto.taskId, changeTitleDto.newTitle),
            HttpStatus.OK.value()
        )
        return ResponseEntity(response, HttpStatus.OK)
    }
}