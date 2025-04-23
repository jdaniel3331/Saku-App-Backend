package com.jdaniel.sakuappapi.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestException extends RuntimeException{
    private String status;
    private String message;
    private int code;

    public RequestException(String status, String message, int code) {
        super(message);
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
