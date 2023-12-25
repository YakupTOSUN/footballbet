package com.bet.sportoto.game.service;

import com.bet.sportoto.betround.entity.BetRound;
import com.bet.sportoto.betround.entity.BetStatus;
import com.bet.sportoto.betround.repository.BetRoundRepository;
import com.bet.sportoto.betround.service.BetRoundManager;
import com.bet.sportoto.exception.BetRoundFullGameException;
import com.bet.sportoto.exception.NotFoundBetGameException;
import com.bet.sportoto.game.controller.request.GamePostRequest;
import com.bet.sportoto.game.controller.request.GameResultPutRequest;
import com.bet.sportoto.game.entity.Game;
import com.bet.sportoto.game.entity.GameDto;
import com.bet.sportoto.game.repository.GameRepository;
import com.bet.sportoto.mapper.ModelMapperManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameManager implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapperManager modelMapperManager;
    private final BetRoundManager betRoundManager;
    private final BetRoundRepository betRoundRepository;

    @Override
    public GameDto postGame(GamePostRequest request, String betRoundId) {
        BetRound betRound = betRoundManager.findById(betRoundId);
        if(betRound.getGameList().stream().count()>=13){
            throw new BetRoundFullGameException("A newsletter can have up to 13 matches");
        }
        Game game = new Game();
        game = game.create(request);
        game = gameRepository.save(game);
        betRoundManager.addGame(betRound, game);
        if(betRound.getGameList().stream().count()==13)
            betRound.setBetStatus(BetStatus.PLANNED);
        betRoundRepository.save(betRound);
        return modelMapperManager.forResponse().map(game, GameDto.class);
    }

    @Override
    public GameDto putGameResult(String gameId, GameResultPutRequest request, String betRoundId) {
        Game game = findById(gameId);
        game = game.update(request);
        game = gameRepository.save(game);
        BetRound betRound = betRoundManager.findById(betRoundId);
        betRound.setBetStatus(BetStatus.ENDED);
        betRoundRepository.save(betRound);
        return modelMapperManager.forResponse().map(game, GameDto.class);
    }

    @Override
    public Game findById(String id) {
        return gameRepository.findById(id).orElseThrow(()-> new NotFoundBetGameException("Game not found!"));
    }
}
