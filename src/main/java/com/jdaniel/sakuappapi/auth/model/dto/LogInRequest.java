package com.jdaniel.sakuappapi.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LogInRequest(
        @NotNull
        @NotBlank
        @JsonProperty("email")
        String email,
        @NotNull
        @NotBlank
        @JsonProperty("password")
        String password
) {
}
