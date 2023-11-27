package com.example.rest.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rest.entity.Activity;
import com.example.rest.entity.Profile;
import com.example.rest.entity.Role;
import com.example.rest.entity.User;
import com.example.rest.repository.RoleRepository;
import com.example.rest.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    public List<User> all() {
        List<User> listUser = new ArrayList<>();
        this.userRepository.findAll().forEach(listUser::add);

        return ((Collection<?>) listUser).size() > 0 ? listUser : null;
    }

    public User save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        user.getProfile().setUser(user);

        List<Role> roles = new ArrayList<>();
        Role defaultRole = this.roleRepository.findByName("ROLE_USER").get();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            roles.add(defaultRole);
        } else {
            roles = user.getRoles().stream().map(
                role -> this.roleRepository
                    .findByName(role.getName())
                    .orElse(defaultRole)
            ).distinct().collect(Collectors.toList());
        }
        user.setRoles(roles);

        Activity activity = new Activity(this.request.getRemoteAddr(), LocalDateTime.now(), LocalDateTime.now(), null, user);
        user.setActivity(activity);

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

        Profile profile = updateUser.getProfile();
        profile.setFirstName(user.getProfile().getFirstName());
        profile.setLastName(user.getProfile().getLastName());
        profile.setVerify(user.getProfile().isVerify());

        List<Role> roles = new ArrayList<>();
        Role defaultRole = this.roleRepository.findByName("ROLE_USER").get();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            roles.add(defaultRole);
        } else {
            roles = user.getRoles().stream().map(
                role -> this.roleRepository
                    .findByName(role.getName())
                    .orElse(defaultRole)
            ).distinct().collect(Collectors.toList());
        }
        updateUser.setRoles(roles);

        Activity activity = updateUser.getActivity();
        activity.setLastLoginIP(this.request.getRemoteAddr());
        activity.setLastLoginAt(LocalDateTime.now());

        activity.setCreatedAt(activity.getCreatedAt());
        activity.setUpdatedAt(LocalDateTime.now());

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
