package com.bet.sportoto.game.controller;

import com.bet.sportoto.exception.ErrorResponse;
import com.bet.sportoto.game.controller.request.GamePostRequest;
import com.bet.sportoto.game.controller.request.GameResultPutRequest;
import com.bet.sportoto.game.entity.GameDto;
import com.bet.sportoto.game.service.GameService;
import com.bet.sportoto.security.annotations.OnlyAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @OnlyAdmin
    @Operation(
            summary = "Betting round by getting the betting round ID \n" +
                    "add game",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDto postGame(@RequestBody GamePostRequest request, @RequestParam String betRoundId) {
        return gameService.postGame(request, betRoundId);
    }

    @OnlyAdmin
    @Operation(
            description = "Adding the match result by taking the Match ID and Coupon ID",
            summary = "Adding the game result by taking the bet round  ID and game  ID",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDto putGameResult(@RequestParam String gameId, @RequestBody GameResultPutRequest request, @RequestParam String betRoundId) {
        return gameService.putGameResult(gameId, request, betRoundId);
    }

}
