package com.bet.sportoto.userbetround.service;

import com.bet.sportoto.betround.entity.BetRound;
import com.bet.sportoto.betround.entity.BetStatus;
import com.bet.sportoto.betround.service.BetRoundManager;
import com.bet.sportoto.exception.NotFoundPlannedBetRoundsException;
import com.bet.sportoto.exception.NotFoundUserBetRoundException;
import com.bet.sportoto.exception.UserBetNotFullException;
import com.bet.sportoto.game.entity.Game;
import com.bet.sportoto.mail.MailService;
import com.bet.sportoto.mapper.ModelMapperManager;
import com.bet.sportoto.user.entity.User;
import com.bet.sportoto.user.service.UserManager;
import com.bet.sportoto.userbet.entity.Selection;
import com.bet.sportoto.userbet.entity.UserBet;
import com.bet.sportoto.userbet.repository.UserBetRepository;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import com.bet.sportoto.userbetround.entity.UserBetRoundDto;
import com.bet.sportoto.userbetround.repository.UserBetRoundRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserBetRoundManager implements UserBetRoundService{
    private final UserBetRoundRepository userBetRoundRepository;
    private final ModelMapperManager modelMapperManager;
    private final BetRoundManager betRoundManager;
    private final UserManager userManager;
    private final UserBetRepository userBetRepository;
    private final MailService mailService;
    private final ModelMapper modelMapper;


    @Override
    public UserBetRoundDto postUserBetRound(String userId, String betRoundId) {
        BetRound betRound = betRoundManager.findById(betRoundId);
        if(betRound.getBetStatus().equals(BetStatus.PLANNED) && betRound.getPlayDt().isAfter(LocalDateTime.now())){
            User user = userManager.findById(userId);
            UserBetRound userBetRound = new UserBetRound();
            userBetRound.create();
            if(betRound.getPlayDt().isBefore(userBetRound.getCreateDt()))
                throw new NotFoundPlannedBetRoundsException("You cannot make active bet at this time because games started");
           userBetRoundRepository.save(userBetRound);
           userManager.addUserBetRound(user, userBetRound);
           betRoundManager.addUserBetRound(betRound, userBetRound);
           return   modelMapperManager.forResponse().map(userBetRound, UserBetRoundDto.class);
            } else if (betRound.getBetStatus().equals(BetStatus.ENDED)) {
               throw new NotFoundPlannedBetRoundsException("bets off");
           }
        throw new NotFoundPlannedBetRoundsException("No scheduled betting rounds found");
    }

    @Override
    public UserBetRound findById(String userBetRoundId) {
        return userBetRoundRepository.findById(userBetRoundId).orElseThrow(()-> new NotFoundUserBetRoundException("User bet round not found!"));
    }

    public void addUserBet(UserBet userBet, UserBetRound userBetRound) {
        userBetRound.addUserBet(userBet);
        userBetRoundRepository.save(userBetRound);
    }

    @Override
    public String userBettingRoundResult(String betRoundId, String userId) {
        BetRound betRound = betRoundManager.findById(betRoundId);
        User user = userManager.findById(userId);
        int asa = user.getUserBetRoundList().size();
        if(asa<13){
            throw new UserBetNotFullException("invalid bet round because bet round should have 13 bets");
        }
        for(int y=0 ; y<asa ; y++ ){
            UserBetRound userBetRound = user.getUserBetRoundList().get(y);

        for (int i=0 ; i<=12 ;i++){
            Game game = betRound.getGameList().get(i);
            UserBet userBet = userBetRound.getUserBetList().get(i);
            searchGameUserBet(game, userBet);
        }
       int a = numberOfCorrectGuesses(userBetRound);
        int b = 13 - a;
        String c ;
        if(b<10){
             c = "you lost";
        }else
         c = "you win";
        String result = "Your number of correct guesses: "+ a +"\n"+" number of wrong predictions: "+ b+ "\n" +
                "Your coupon result: "  + c;
         mailService.couponResult(user.getMail(),result) ;
        }
        return "bet round result user's sended";
    }

    @Override
    public List<UserBetRoundDto> getAllUserBetRound( String userId, String betRoundId) {
        BetRound betRound = betRoundManager.findById(betRoundId);
        User user = userManager.findById(userId);
        List<UserBetRound> userBetRoundList = new ArrayList<>();
        for(int y=0; y<user.getUserBetRoundList().size() ; y++) {
    Optional<UserBetRound> betRound1 =  userBetRoundRepository.findById(user.getUserBetRoundList().get(y).getId());
    for(int i=0; i<betRound.getUserBetRoundList().size() ; i++) {
       if( betRound.getUserBetRoundList().get(i).getId().equals(betRound1.get().getId())){
           userBetRoundList.add(y,betRound.getUserBetRoundList().get(i));
       }
    }}
     List<UserBetRoundDto> dtoList = userBetRoundList.stream().map((betRound1) -> modelMapper.map(betRound1, UserBetRoundDto.class)).toList();

        return dtoList;
    }

    public int numberOfCorrectGuesses(UserBetRound userBetRound){
        AtomicInteger guessThatHolds = new AtomicInteger();
        for(int i = 0 ; i<=12 ; i++){
            UserBet userBet = userBetRound.getUserBetList().get(i);
            if(userBet.isGuesCorrect())
                guessThatHolds.set(guessThatHolds.get() + 1);
        }
            return guessThatHolds.get();
    }
    public void searchGameUserBet(Game game,UserBet userBet) {
        int gameResult1 = game.getGameResult().getScoreFirstTeam();
        int gameResult2 = game.getGameResult().getScoreSecondTeam();
        Selection selection = userBet.getSelection();
       if ((gameResult1 < gameResult2) && (selection.equals(Selection.SECOND))){
            userBet.setGuesCorrect(true);
        }  else if  ((gameResult1>gameResult2) && (selection.equals(Selection.FIRST))){
            userBet.setGuesCorrect(true);
        }  else userBet.setGuesCorrect(selection.equals(Selection.DRAW));
        userBetRepository.save(userBet);
    }
}
