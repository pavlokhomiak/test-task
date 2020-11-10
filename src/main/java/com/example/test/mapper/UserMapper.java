package com.example.test.mapper;

import com.example.test.model.User;
import com.example.test.model.dto.UserRequestDto;
import com.example.test.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setRoles(user.getRoles());
        dto.setActive(user.getActive());
        return dto;
    }
}
