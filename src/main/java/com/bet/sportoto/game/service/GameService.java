package com.bet.sportoto.game.service;

import com.bet.sportoto.game.controller.request.GamePostRequest;
import com.bet.sportoto.game.controller.request.GameResultPutRequest;
import com.bet.sportoto.game.entity.Game;
import com.bet.sportoto.game.entity.GameDto;

public interface GameService {
    GameDto postGame(GamePostRequest request, String betRoundId);

    GameDto putGameResult(String gameId, GameResultPutRequest request, String betRoundId);

    Game findById(String id);
}
