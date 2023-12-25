package com.bet.sportoto.game.entity;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class GameDto {
    private String id;
    private String firstTeam;
    private String secondTeam;
    @Embedded
    private GameResult gameResult;
}
