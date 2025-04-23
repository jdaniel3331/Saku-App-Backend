package com.jdaniel.sakuappapi.auth.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest (
        @NotNull
        @NotBlank
        String firstName,
        String secondName,
        @NotNull
        @NotBlank
        String firstSurname,
        String secondSurname,
        @NotNull
        @NotBlank
        LocalDate dateOfBirth,
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        @Size(min = 8, max = 20)
        String password
){}
