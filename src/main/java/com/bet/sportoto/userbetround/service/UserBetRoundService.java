package com.bet.sportoto.userbetround.service;

import com.bet.sportoto.userbet.entity.UserBet;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import com.bet.sportoto.userbetround.entity.UserBetRoundDto;

import java.util.List;

public interface UserBetRoundService {
    UserBetRoundDto postUserBetRound(String userId, String betRoundId);

    UserBetRound findById(String userBetRoundId);

    void addUserBet(UserBet userBet, UserBetRound userBetRound);

    String userBettingRoundResult( String betRoundId, String userId);

    List<UserBetRoundDto> getAllUserBetRound( String userId, String betRoundId);
}
