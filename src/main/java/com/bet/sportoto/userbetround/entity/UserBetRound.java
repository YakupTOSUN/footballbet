package com.bet.sportoto.userbetround.entity;


import com.bet.sportoto.exception.GeneralException;
import com.bet.sportoto.userbet.entity.UserBet;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "UserBetRound")
public class UserBetRound implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDt;
    private String title;
    @OneToMany
    @JoinTable(name = "userBetRound_userBets",
            joinColumns = {@JoinColumn(name = "userBetRoundId")},
            inverseJoinColumns = {@JoinColumn(name = "userBetId")})
    private List<UserBet> userBetList;
    private int correctGuessedCount;

    public UserBetRound create() {
        setTitle("Cupon"+ LocalDateTime.now());
        setCreateDt(LocalDateTime.now());
        return this;
    }

    public void addUserBet(UserBet userBet) {
        boolean isPresent = this.userBetList.contains(userBet);
        if(!isPresent)
            this.userBetList.add(userBet);
        else {
            throw new GeneralException("You have already added this prediction");
        }
    }
}
