package com.example.rest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rest.entity.Role;
import com.example.rest.entity.User;
import com.example.rest.repository.RoleRepository;
import com.example.rest.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> all() {
        List<User> listUser = new ArrayList<>();
        this.userRepository.findAll().forEach(listUser::add);

        return ((Collection<?>) listUser).size() > 0 ? listUser : null;
    }

    public User save(User user) {
        if (user.getRoles().isEmpty()) {
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

    public User find(String email) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);

        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public User update(Long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return null;
        }

        User updateUser = optionalUser.get();

        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(this.passwordEncoder.encode(user.getPassword()));

        updateUser.setRoles(user.getRoles());
        updateUser.setProfile(user.getProfile());
        updateUser.setActivity(user.getActivity());

        return this.userRepository.save(updateUser);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User current() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null && authentication instanceof AnonymousAuthenticationToken && !authentication.isAuthenticated()) {
            return null;
        }

        Optional<User> optionalUser = this.userRepository.findByEmail(authentication.getName());

        return optionalUser.isPresent() ? optionalUser.get() : null;
    }
}
