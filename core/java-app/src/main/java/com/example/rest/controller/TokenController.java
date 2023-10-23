package com.example.rest.controller;

import java.time.LocalDateTime;
// import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.TokenDto;
import com.example.rest.entity.User;
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
    public ResponseEntity<?> registration(@RequestBody TokenDto tokenDto, HttpServletRequest request) {
        // Enumeration headerNames = request.getHeaderNames();
        // while (headerNames.hasMoreElements()) {
        //     String key = (String) headerNames.nextElement();
        //     System.out.println(key + " --- " + request.getHeader(key));
        // }

        if (this.userService.find(tokenDto.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = new User(
            tokenDto.getName(),                         // name
            tokenDto.getEmail(),                        // email
            tokenDto.getPassword()                      // password
        );

        user.setLastLoginIP(request.getRemoteAddr());   // last login ip
        user.setLastLoginAt(LocalDateTime.now());       // last login at

        user.setCreatedAt(LocalDateTime.now());         // created at
        user.setUpdatedAt(null);              // updated at

        this.userService.save(user);

        String jwtString = this.jwtService.create(tokenDto.getEmail(), tokenDto.getPassword());

        return new ResponseEntity<>(jwtString, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody TokenDto tokenDto, HttpServletRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    tokenDto.getEmail(), tokenDto.getPassword()
                )
            );
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwtString = this.jwtService.create(tokenDto.getEmail(), tokenDto.getPassword());

        return new ResponseEntity<>(jwtString, HttpStatus.CREATED);
    }
}
