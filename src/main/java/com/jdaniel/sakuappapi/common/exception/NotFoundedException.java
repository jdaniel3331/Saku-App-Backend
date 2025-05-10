package com.jdaniel.sakuappapi.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class NotFoundedException extends RuntimeException{
    private String status;
    private String message;
    private int code;

    public NotFoundedException(String message, String status) {
        super(message);
        this.status = status;
        this.code = HttpStatus.NOT_FOUND.value();
        this.message = message;
    }
}
