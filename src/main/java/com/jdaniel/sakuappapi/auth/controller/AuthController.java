package com.jdaniel.sakuappapi.auth.controller;

import com.jdaniel.sakuappapi.auth.model.dto.RegisterRequest;
import com.jdaniel.sakuappapi.auth.service.AuthServiceImpl;
import com.jdaniel.sakuappapi.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> signIn(@RequestBody @Valid RegisterRequest registerRequest) {
        ApiResponse response = new ApiResponse(HttpStatus.CREATED.name(),authService.register(registerRequest), HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
