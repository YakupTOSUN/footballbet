package com.bet.sportoto.betround.service;

import com.bet.sportoto.betround.controller.request.PostBetRoundRequest;
import com.bet.sportoto.betround.entity.BetRound;
import com.bet.sportoto.betround.entity.BetRoundDto;
import com.bet.sportoto.game.entity.Game;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BetRoundService {
    BetRoundDto postBetRound(PostBetRoundRequest request);

    BetRound findById(String betRoundId);

    void addGame(BetRound betRound, Game game);
     void addUserBetRound(BetRound betRound, UserBetRound userBetRound);

    Page<BetRoundDto> getPlannedBetRounds(Pageable pageable);

    Page<BetRoundDto> getEndedBetRounds(Pageable pageable);

    Page<BetRoundDto> getAllBetRounds(Pageable pageable);

    void statusChangePlanned(String betroundId);


}
