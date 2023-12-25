package com.bet.sportoto.betround.entity;


import com.bet.sportoto.betround.controller.request.PostBetRoundRequest;
import com.bet.sportoto.exception.GeneralException;
import com.bet.sportoto.game.entity.Game;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "BetRound")
public class BetRound implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime playDt;
    private String title;
    @Enumerated(EnumType.STRING)
    private BetStatus betStatus;
    @OneToMany
    @JoinTable(name = "betRound_games",
            joinColumns = {@JoinColumn(name = "betRoundId")},
            inverseJoinColumns = {@JoinColumn(name = "gameId")})
    private List<Game> gameList;
    @OneToMany
    @JoinTable(name = "betRound_userBetRound",
            joinColumns = {@JoinColumn(name = "betRoundId")},
            inverseJoinColumns = {@JoinColumn(name = "userBetRoundId")})
    private List<UserBetRound> userBetRoundList;

    public BetRound create(PostBetRoundRequest request) {
        setPlayDt(request.getPlayDt());
        setTitle(request.getTitle());
        betStatus = BetStatus.CREATED;
        return this;
    }

    public void addGame(Game game) {
        boolean isPresent = this.gameList.contains(game);
        if (!isPresent)
            this.gameList.add(game);
        else {
            throw new GeneralException("You have already added this game");
        }
    }

    public void addUserBetRound(UserBetRound userBetRound){
        boolean isPresent = this.userBetRoundList.contains(userBetRound);
        if(!isPresent)
            this.userBetRoundList.add(userBetRound);
        else {
            throw new GeneralException("You have already added this user bet round");
        }
    }
}
