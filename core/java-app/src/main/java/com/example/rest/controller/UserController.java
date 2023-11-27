package com.example.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.User;
import com.example.rest.request.UserRequest;
import com.example.rest.response.UserResponse;
import com.example.rest.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    UserController() {
        this.objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @GetMapping
    public ResponseEntity<?> index() {
        final List<User> listUser = this.userService.all();

        if (listUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final List<UserResponse> listUserResponse = listUser.stream()
            .map(user -> this.objectMapper.convertValue(user, UserResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(listUserResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody UserRequest userRequest) {
        User user = new User(
            userRequest.getEmail(),
            userRequest.getPassword()
        );

        user.setProfile(userRequest.getProfile());

        user.setRoles(userRequest.getRoles());

        final User storeUser = this.userService.save(user);

        final UserResponse userResponse = this.objectMapper.convertValue(storeUser, UserResponse.class);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        final User user = this.userService.find(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final UserResponse userResponse = this.objectMapper.convertValue(user, UserResponse.class);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User user = new User(
            userRequest.getEmail(),
            userRequest.getPassword()
        );

        user.setProfile(userRequest.getProfile());

        user.setRoles(userRequest.getRoles());

        final User updateUser = this.userService.update(id, user);

        if (updateUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final UserResponse userResponse = this.objectMapper.convertValue(updateUser, UserResponse.class);

        return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/whoami")
    public ResponseEntity<?> whoami() {
        final UserResponse userResponse = this.objectMapper.convertValue(this.userService.current(), UserResponse.class);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
