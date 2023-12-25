package com.bet.sportoto.game.controller.request;

import lombok.Data;

@Data
public class GameResultPutRequest {
    private Integer scoreFirstTeam;
    private Integer scoreSecondTeam;
}
