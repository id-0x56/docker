package com.example.test;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public List<User> index() {
        return List.of(
                new User(1, "Ivan", "ivan@mail.com"),
                new User(2, "Alex", "alex@mail.com"),
                new User(3, "Mike", "mike@mail.com")
        );
    }

    // @PostMapping
    // public User store(@RequestBody User user) {
    //     //
    // }

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return new User(id, "Alex", "alex@mail.com");
    }

    // @PutMapping("/{id}")
    // public User update(@PathVariable Long id, @RequestBody User user) {
    //     //    
    // }

    // @DeleteMapping("/{id}")
    // public void destroy(@PathVariable Long id) {
    //     //
    // }
}
