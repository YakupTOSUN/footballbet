package com.bet.sportoto.account.service;

import com.bet.sportoto.account.controller.request.LoginRequest;
import com.bet.sportoto.account.controller.request.RegisterRequest;
import com.bet.sportoto.user.entity.UserDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    UserDto register(RegisterRequest request);

    ResponseEntity<String> login(LoginRequest request);

    UserDto getMe();

    String putPassword(String mail);
}
