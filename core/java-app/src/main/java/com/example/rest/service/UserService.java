package com.example.rest.service;

import java.util.List;

import com.example.rest.entity.User;

public interface UserService {

    public List<User> all();

    public User save(User user);

    public User find(Long id);
    public User find(String email);

    public User update(Long id, User user);

    public void delete(Long id);
    
    public User current();
}
