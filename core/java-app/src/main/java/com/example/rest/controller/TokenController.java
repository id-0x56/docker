package com.example.rest.controller;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.Activity;
import com.example.rest.entity.Profile;
import com.example.rest.entity.User;
import com.example.rest.request.TokenRequest;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<?> token(TokenRequest tokenRequest, HttpServletRequest request) {
        if (this.userService.find(tokenRequest.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = new User(
            tokenRequest.getEmail(),
            tokenRequest.getPassword()
        );

        user.setRoles(
            Collections.emptyList()
        );

        user.setProfile(
            new Profile("", "", false, user)
        );

        user.setActivity(
            new Activity(request.getRemoteAddr(), LocalDateTime.now(), LocalDateTime.now(), null, user)
        );

        final User storeUser = this.userService.save(user);

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setEmail(storeUser.getEmail());
        tokenResponse.setToken(
            this.jwtService.create(storeUser.getEmail(), storeUser.getPassword())
        );

        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(TokenRequest tokenRequest, HttpServletRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    tokenRequest.getEmail(), tokenRequest.getPassword()
                )
            );
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setEmail(tokenRequest.getEmail());
        tokenResponse.setToken(
            this.jwtService.create(tokenRequest.getEmail(), this.passwordEncoder.encode(tokenRequest.getPassword()))
        );

        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }
}
