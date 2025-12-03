package com.github.jianlu8023.example.integration.middleware.exceptions;

public class CreateFailException extends RuntimeException {
    public CreateFailException() {
    }

    public CreateFailException(String message) {
        super(message);
    }

    public CreateFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateFailException(Throwable cause) {
        super(cause);
    }

    public CreateFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
