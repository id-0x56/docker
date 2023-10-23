package com.example.rest.mapper;

import com.example.rest.dto.UserDto;
import com.example.rest.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());

        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        userDto.setLastLoginIP(user.getLastLoginIP());
        userDto.setLastLoginAt(user.getLastLoginAt());

        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User(
            userDto.getName(),
            userDto.getEmail(),
            userDto.getPassword()
        );

        user.setId(userDto.getId());

        user.setLastLoginIP(userDto.getLastLoginIP());
        user.setLastLoginAt(userDto.getLastLoginAt());

        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());

        return user;
    }
}
