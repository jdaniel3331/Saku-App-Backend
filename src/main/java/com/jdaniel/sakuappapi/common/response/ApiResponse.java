package com.jdaniel.sakuappapi.common.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ApiResponse<T>(
        String timestamp,
        String status,
        String message,
        int code,
        T data
) {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //set date to now
    public ApiResponse(String status, String message, int code) {
        this(LocalDateTime.now().format(formatter), status, message, code, null);
    }
    public ApiResponse(String status, String message, int code, T data) {
        this(LocalDateTime.now().format(formatter), status, message, code, data);
    }

}
