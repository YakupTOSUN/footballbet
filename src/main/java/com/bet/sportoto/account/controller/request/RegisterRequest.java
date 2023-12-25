package com.bet.sportoto.account.controller.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
}
