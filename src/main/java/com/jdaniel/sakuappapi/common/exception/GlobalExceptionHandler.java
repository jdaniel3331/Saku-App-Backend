package com.jdaniel.sakuappapi.common.exception;

import com.jdaniel.sakuappapi.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ApiResponse> handleRequestException(RequestException e) {
        ApiResponse response = new ApiResponse(e.getStatus(), e.getMessage(), e.getCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getCode()));
    }
}
