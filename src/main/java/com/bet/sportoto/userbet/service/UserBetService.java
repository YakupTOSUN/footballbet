package com.bet.sportoto.userbet.service;

import com.bet.sportoto.userbet.entity.Selection;
import com.bet.sportoto.userbet.entity.UserBet;
import com.bet.sportoto.userbet.entity.UserBetDto;

public interface UserBetService {
    UserBetDto postUserBet(Selection selection, String userBetRoundId);

    UserBet findById(String id);
}
