package com.example.rest.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.rest.entity.User;
import com.example.rest.service.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> index() {
        final List<User> listUser = this.userService.all();

        return listUser != null
            ? new ResponseEntity<>(listUser, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> store(@RequestBody User user) {
        final User storeUser = this.userService.save(user);

        return new ResponseEntity<>(storeUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable long id) {
        final User user = this.userService.find(id);

        return user != null
            ? new ResponseEntity<>(user, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User user) {
        final User updateUser = this.userService.update(id, user);

        return updateUser != null
            ? new ResponseEntity<>(updateUser, HttpStatus.ACCEPTED)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> destroy(@PathVariable long id) {
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
