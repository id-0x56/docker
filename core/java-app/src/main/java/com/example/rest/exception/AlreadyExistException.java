package com.example.rest.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }
}
