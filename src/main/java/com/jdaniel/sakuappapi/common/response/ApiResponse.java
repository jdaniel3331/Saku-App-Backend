package com.jdaniel.sakuappapi.common.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record ApiResponse(
        LocalDateTime timestamp,
        String status,
        String message,
        int code
) {
    //set date to now
    public ApiResponse(String status, String message, int code) {
        this(LocalDateTime.now(), status, message, code);
    }
}
