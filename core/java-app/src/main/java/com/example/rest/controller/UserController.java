package com.example.rest.controller;

import java.time.LocalDateTime;
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

import com.example.rest.dto.UserDto;
import com.example.rest.entity.User;
import com.example.rest.mapper.UserMapper;
import com.example.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

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
    public ResponseEntity<?> store(@RequestBody UserDto userDto, HttpServletRequest request) {
        User user = UserMapper.toEntity(userDto);

        user.setLastLoginIP(request.getRemoteAddr());
        user.setLastLoginAt(LocalDateTime.now());

        user.setCreatedAt(LocalDateTime.now());

        this.userService.save(user);

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
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDto userDto, HttpServletRequest request) {
        User user = UserMapper.toEntity(userDto);

        user.setLastLoginIP(request.getRemoteAddr());
        user.setLastLoginAt(LocalDateTime.now());

        user.setUpdatedAt(LocalDateTime.now());

        final User updateUser = this.userService.update(id, user);

        if (updateUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final UserDto updateUserDto = UserMapper.toDto(updateUser);

        return new ResponseEntity<>(updateUserDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
