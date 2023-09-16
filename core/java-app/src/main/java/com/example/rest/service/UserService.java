package com.example.rest.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.entity.User;
import com.example.rest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> all() {
        List<User> listUser = new ArrayList<>();
        this.userRepository.findAll().forEach(listUser::add);

        return ((Collection<?>) listUser).size() > 0 ? listUser : null;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User find(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public User update(Long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return null;
        }

        User updateUser = optionalUser.get();

        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());

        return this.userRepository.save(updateUser);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}
