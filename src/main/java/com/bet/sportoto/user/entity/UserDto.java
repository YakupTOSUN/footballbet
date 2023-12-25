package com.bet.sportoto.user.entity;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private UserRole userRole;
}
