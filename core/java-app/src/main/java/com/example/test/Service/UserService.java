package com.example.test.Service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.test.Entity.User;
import com.example.test.Repository.UserRepository;

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

    public User find(long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public User update(long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return null;
        }

        User updateUser = optionalUser.get();

        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());

        return this.userRepository.save(updateUser);
    }

    public void delete(long id) {
        this.userRepository.deleteById(id);
    }
}
