package com.example.rest.request;

import jakarta.validation.constraints.NotNull;

public class TokenRequest {
    
    @NotNull(message = "Invalid email: Email is NULL")
    private String email;

    @NotNull(message = "Invalid password: Password is NULL")
    private String password;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
