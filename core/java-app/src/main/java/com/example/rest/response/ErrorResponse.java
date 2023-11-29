package com.example.rest.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int code;

    private String message;

    private LocalDateTime date;

    public ErrorResponse(int code, String message, LocalDateTime date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
