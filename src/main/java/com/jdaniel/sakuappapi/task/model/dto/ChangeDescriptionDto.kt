package com.jdaniel.sakuappapi.task.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ChangeDescriptionDto(
    @JsonProperty("new_description")
    @NotNull
    @NotBlank
    val newDescription: String
)
