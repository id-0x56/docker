package com.example.rest.response;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private int code;

    private List<String> messages;

    private LocalDateTime date;

    public ErrorResponse(int code, List<String> messages, LocalDateTime date) {
        this.code = code;
        this.messages = messages;
        this.date = date;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
