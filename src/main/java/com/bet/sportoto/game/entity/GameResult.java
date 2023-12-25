package com.bet.sportoto.game.entity;

import com.bet.sportoto.game.controller.request.GameResultPutRequest;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Data
@Embeddable
public class GameResult implements Serializable {
    private Integer scoreFirstTeam;
    private Integer scoreSecondTeam;

    public GameResult create(GameResultPutRequest request) {
        setScoreFirstTeam(request.getScoreFirstTeam());
        setScoreSecondTeam(request.getScoreSecondTeam());
        return this;
    }
}
