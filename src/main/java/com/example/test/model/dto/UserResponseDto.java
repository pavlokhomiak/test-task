package com.example.test.model.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String password;
    private String roles;
    private int active;
}
