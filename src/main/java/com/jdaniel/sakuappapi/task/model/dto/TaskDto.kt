package com.jdaniel.sakuappapi.task.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class TaskDto(
    @JsonProperty("user_id")
    val taskId: Long,
    val title: String,
    val description: String?,
    @JsonProperty("created_at")
    val createdAt: LocalDate,
    @JsonProperty("due_date")
    val dueDate: LocalDate?,
    val category: Long?,
    @JsonProperty("task_state")
    val taskState: Short,
    @JsonProperty("priority_level")
    val priorityLevel: Short,
)
