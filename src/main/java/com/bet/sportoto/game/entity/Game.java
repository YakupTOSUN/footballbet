package com.bet.sportoto.game.entity;

import com.bet.sportoto.game.controller.request.GamePostRequest;
import com.bet.sportoto.game.controller.request.GameResultPutRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstTeam;
    private String secondTeam;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime playDt;
    @Embedded
    private GameResult gameResult;


    public Game create(GamePostRequest request) {
        setFirstTeam(request.getFirstTeam());
        setSecondTeam(request.getSecondTeam());
        setGameResult(getGameResult());
        setPlayDt(request.getPlayDt());
        return this;
    }

    public Game update(GameResultPutRequest request) {
        GameResult gameResult1 = new GameResult();
        gameResult1.create(request);
        setGameResult(gameResult1);
        return this;
    }

}
