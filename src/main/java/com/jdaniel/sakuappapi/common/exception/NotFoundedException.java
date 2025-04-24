package com.jdaniel.sakuappapi.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotFoundedException extends RuntimeException{
    private String status;
    private String message;
    private int code;

    public NotFoundedException(String message, String status, int code) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
