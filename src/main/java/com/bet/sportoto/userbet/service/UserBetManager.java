package com.bet.sportoto.userbet.service;


import com.bet.sportoto.exception.GeneralException;
import com.bet.sportoto.exception.NotFoundUserBetException;
import com.bet.sportoto.mapper.ModelMapperManager;
import com.bet.sportoto.userbet.entity.Selection;
import com.bet.sportoto.userbet.entity.UserBet;
import com.bet.sportoto.userbet.entity.UserBetDto;
import com.bet.sportoto.userbet.repository.UserBetRepository;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import com.bet.sportoto.userbetround.service.UserBetRoundManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBetManager implements UserBetService {
    private final UserBetRepository userBetRepository;
    private final ModelMapperManager modelMapperManager;
    private final UserBetRoundManager userBetRoundManager;


    @Override
    public UserBetDto postUserBet(Selection selection, String userBetRoundId) {
        UserBetRound userBetRound = userBetRoundManager.findById(userBetRoundId);
        long userBetCount = userBetRound.getUserBetList().stream().count();
        if(userBetCount>=13){
            throw new GeneralException("There cannot be more than 13 match predictions in one coupon!");
        }
        UserBet userBet = new UserBet();
         userBet.create(selection);
         userBetRepository.save(userBet);
         userBetRoundManager.addUserBet(userBet,userBetRound);
        if(userBetCount==12){
            log.info("user bet roud added,succes");
        }else {
            log.info("You must add "+ (12-userBetCount) + " more ");
        }
        return modelMapperManager.forResponse().map(userBet, UserBetDto.class);
    }

    @Override
    public UserBet findById(String id) {
        return userBetRepository.findById(id).orElseThrow(()-> new NotFoundUserBetException("User bet not found!"));
    }
}
