package com.bet.sportoto.user.entity;


import com.bet.sportoto.account.controller.request.RegisterRequest;
import com.bet.sportoto.exception.GeneralException;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDt;
    private String firstname;
    private String lastname;
    private String password;
    private String mail;
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.ROLE_USER;
    @OneToMany
    @JoinTable(name = "user_userBetRound",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "userBetRoundId")})
    private List<UserBetRound> userBetRoundList;

    public User create(RegisterRequest request) {
        this.firstname = request.getFirstname();
        this.lastname = request.getLastname();
        this.password = request.getPassword();
        this.mail = request.getMail();
        this.createDt = LocalDateTime.now();
        this.updateDt = LocalDateTime.now();
        return this;
    }

    public void addUserBetRound(UserBetRound userBetRound) {
        boolean isPresent = this.userBetRoundList.contains(userBetRound);
        if (!isPresent)
            this.userBetRoundList.add(userBetRound);
        else {
            throw new GeneralException("You have already added this user bet round");
        }
    }
}