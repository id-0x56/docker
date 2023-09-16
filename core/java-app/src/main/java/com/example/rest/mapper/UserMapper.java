package com.example.rest.mapper;

import com.example.rest.dto.UserDto;
import com.example.rest.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPassword()
        );
    }

    public static User toEntity(UserDto userDto) {
        return new User(
            userDto.getId(),
            userDto.getName(),
            userDto.getEmail(),
            userDto.getPassword()
        );
    }
}
