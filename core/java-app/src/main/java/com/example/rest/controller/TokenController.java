package com.example.rest.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.User;
import com.example.rest.request.UserRequest;
import com.example.rest.response.TokenResponse;
import com.example.rest.service.JwtService;
import com.example.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/registration")
    public ResponseEntity<?> token(UserRequest userRequest, HttpServletRequest request) {
        if (this.userService.find(userRequest.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = new User(
            userRequest.getName(),                      // name
            userRequest.getEmail(),                     // email
            userRequest.getPassword()                   // password
        );

        user.setLastLoginIP(request.getRemoteAddr());   // last login ip
        user.setLastLoginAt(LocalDateTime.now());       // last login at

        user.setCreatedAt(LocalDateTime.now());         // created at
        user.setUpdatedAt(null);              // updated at

        this.userService.save(user);

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setName(userRequest.getName());
        tokenResponse.setEmail(userRequest.getEmail());
        tokenResponse.setToken(this.jwtService.create(userRequest.getEmail(), userRequest.getPassword()));

        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(UserRequest userRequest, HttpServletRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userRequest.getEmail(), userRequest.getPassword()
                )
            );
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setName(userRequest.getName());
        tokenResponse.setEmail(userRequest.getEmail());
        tokenResponse.setToken(this.jwtService.create(userRequest.getEmail(), userRequest.getPassword()));

        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }
}
