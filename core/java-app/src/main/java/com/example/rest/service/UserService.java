package com.example.rest.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rest.entity.Role;
import com.example.rest.entity.User;
import com.example.rest.repository.RoleRepository;
import com.example.rest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> all() {
        List<User> listUser = new ArrayList<>();
        this.userRepository.findAll().forEach(listUser::add);

        return ((Collection<?>) listUser).size() > 0 ? listUser : null;
    }

    public User save(User user) {
        if (user.getRoles() == null) {
            Role role = this.roleRepository.findByName("ROLE_USER").get();
            user.setRoles(List.of(role));
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    public User find(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public User find(String name) {
        Optional<User> optionalUser = this.userRepository.findByName(name);

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
        updateUser.setPassword(this.passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(updateUser);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}
