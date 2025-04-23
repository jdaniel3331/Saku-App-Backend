package com.jdaniel.sakuappapi.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest (
        @NotNull
        @NotBlank
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("second_name")
        String secondName,
        @NotNull
        @NotBlank
        @JsonProperty("first_surname")
        String firstSurname,
        @JsonProperty("second_surname")
        String secondSurname,
        @NotNull
        @JsonProperty("date_of_birth")
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
