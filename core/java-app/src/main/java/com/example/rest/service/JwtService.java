package com.example.rest.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    public String create(String... args);

    public String get(String jwt, String key);

    public boolean validate(UserDetails userDetails, String jwt);
}
