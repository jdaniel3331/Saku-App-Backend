package com.jdaniel.sakuappapi.task.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class CreateTaskDto(
    @NotNull
    @NotBlank
    val title: String,
    val description: String,
    @NotNull
    @JsonProperty("due_date")
    val dueDate: LocalDate,
    val category: Long,
    @JsonProperty("priority_level")
    val priorityLevel: Short,
    @NotNull
    @JsonProperty("user_id")
    val userId: Long
    //TODO: Obtener el userId del Token
)
