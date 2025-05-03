package com.jdaniel.sakuappapi.common.response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        LocalDateTime timestamp,
        String status,
        String message,
        int code,
        T data
) {
    //set date to now
    public ApiResponse(String status, String message, int code) {
        this(LocalDateTime.now(), status, message, code, null);
    }
    public ApiResponse(String status, String message, int code, T data) {
        this(LocalDateTime.now(), status, message, code, data);
    }

}
