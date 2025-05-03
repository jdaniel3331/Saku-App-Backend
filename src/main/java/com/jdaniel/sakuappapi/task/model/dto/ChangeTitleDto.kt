package com.jdaniel.sakuappapi.task.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ChangeTitleDto(
    @NotNull
    @JsonProperty("task_id")
    val taskId: Long,
    @NotNull
    @NotBlank
    @JsonProperty("new_title")
    val newTitle: String
)
