package com.example.rest.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest.exception.NotFoundException;
import com.example.rest.response.ErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handle(NotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            exception.getMessage(),
            LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
