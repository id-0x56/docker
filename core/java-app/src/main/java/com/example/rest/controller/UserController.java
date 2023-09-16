package com.example.rest.controller;

import java.util.*;
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

import com.example.rest.dto.UserDto;
import com.example.rest.entity.User;
import com.example.rest.mapper.UserMapper;
import com.example.rest.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> index() {
        final List<User> listUser = this.userService.all();

        if (listUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final List<UserDto> listUserDto = listUser.stream()
            .map(UserMapper::toDto)
            .collect(Collectors.toList());

        return new ResponseEntity<>(listUserDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody UserDto userDto) {
        final User user = this.userService.save(UserMapper.toEntity(userDto));

        final UserDto storeUserDto = UserMapper.toDto(user);

        return new ResponseEntity<>(storeUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        final User user = this.userService.find(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final UserDto userDto = UserMapper.toDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        final User user = this.userService.update(id, UserMapper.toEntity(userDto));

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        final UserDto updateUserDto = UserMapper.toDto(user);

        return new ResponseEntity<>(updateUserDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
