package com.bet.sportoto.betround.service;

import com.bet.sportoto.betround.controller.request.PostBetRoundRequest;
import com.bet.sportoto.betround.entity.BetRound;
import com.bet.sportoto.betround.entity.BetRoundDto;
import com.bet.sportoto.betround.entity.BetStatus;
import com.bet.sportoto.betround.repository.BetRoundRepository;
import com.bet.sportoto.exception.GeneralException;
import com.bet.sportoto.exception.NotFoundBetRoundException;
import com.bet.sportoto.game.entity.Game;
import com.bet.sportoto.mapper.ModelMapperManager;
import com.bet.sportoto.userbetround.entity.UserBetRound;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BetRoundManager implements BetRoundService {
    private final BetRoundRepository betRoundRepository;
    private final ModelMapperManager modelMapperManager;
    private final ModelMapper modelMapper;


    @Override
    public BetRoundDto postBetRound(PostBetRoundRequest request) {
        BetRound betRound = new BetRound();
        betRound.create(request);
        betRoundRepository.save(betRound);
        return modelMapperManager.forResponse().map(betRound, BetRoundDto.class);
    }

    @Override
    public BetRound findById(String betRoundId) {
        return betRoundRepository.findById(betRoundId).orElseThrow(() -> new GeneralException("Bet round Id not found"));
    }


    @Override
    public void addGame(BetRound betRound, Game game) {
        betRound.addGame(game);
        betRoundRepository.save(betRound);
    }

    @Override
    public void addUserBetRound(BetRound betRound, UserBetRound userBetRound) {
        betRound.addUserBetRound(userBetRound);
        betRoundRepository.save(betRound);
    }

    @Override
    public Page<BetRoundDto> getPlannedBetRounds(Pageable pageable) {
        Pageable allPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().isEmpty() ? Sort.by("createDt").ascending() : pageable.getSort()
        );
        Page<BetRound> pageBetRound = betRoundRepository.findByBetStatus(BetStatus.PLANNED, allPageable);
        return pageBetRound.map((betRound) -> modelMapper.map(betRound, BetRoundDto.class));
    }

    @Override
    public Page<BetRoundDto> getEndedBetRounds(Pageable pageable) {
        Pageable allPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().isEmpty() ? Sort.by("createDt").ascending() : pageable.getSort()
        );
        Page<BetRound> pageBetRound = betRoundRepository.findByBetStatus(BetStatus.ENDED, allPageable);
        return pageBetRound.map((betRound) -> modelMapper.map(betRound, BetRoundDto.class));
    }

    @Override
    public Page<BetRoundDto> getAllBetRounds(Pageable pageable) {
        Pageable allPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().isEmpty() ? Sort.by("createDt").ascending() : pageable.getSort());
        Page<BetRound> pageBetRound = betRoundRepository.findAll(allPageable);
        return pageBetRound.map((betRound) -> modelMapper.map(betRound, BetRoundDto.class));
    }

    @Override
    public void statusChangePlanned(String betroundId) {
        BetRound betRound = betRoundRepository.findById(betroundId).orElseThrow(() -> new NotFoundBetRoundException(betroundId + " bet round not found!"));
        betRound.setBetStatus(BetStatus.PLANNED);
        betRoundRepository.save(betRound);
    }


}
