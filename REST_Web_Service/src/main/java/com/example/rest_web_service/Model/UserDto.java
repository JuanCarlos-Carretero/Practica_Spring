package com.example.rest_web_service.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final int id;
    private final String email;
    private final String password;
    private final String fullName;
}
