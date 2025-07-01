package com.studyspring.todocrud.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }
}
