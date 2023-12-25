package com.bet.sportoto.user.service;


import com.bet.sportoto.user.entity.User;
import com.bet.sportoto.user.entity.UserDto;
import com.bet.sportoto.user.entity.UserRole;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    void changedPassword(String userId, String newPassword);

    void changedRole(String userId, UserRole userRole);

    Page<UserDto> getAllUsers(Pageable pageable);

    User findById(String userId);
    void addUserBetRound(User user, UserBetRound userBetRound);
}
