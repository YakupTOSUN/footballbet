package com.bet.sportoto.userbetround.controller;

import com.bet.sportoto.exception.ErrorResponse;
import com.bet.sportoto.security.annotations.OnlyUser;
import com.bet.sportoto.userbetround.entity.UserBetRoundDto;
import com.bet.sportoto.userbetround.service.UserBetRoundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userbetrounds")
@RequiredArgsConstructor
public class UserBetRoundController {
    private final UserBetRoundService userBetRoundService;

    @OnlyUser
    @Operation(
            summary = "User adds user bet round by getting bet round ID",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserBetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBetRoundDto postUserBetRound(@RequestParam String userId,
                                            @RequestParam String betRoundId) {
        return userBetRoundService.postUserBetRound(userId, betRoundId);
    }

    @OnlyUser
    @Operation(
            description = "user bet result update and user sends mail regarding bet result",
            summary = "User bet result update by getting betting round id, user id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserBetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String userBettingRoundResult(@RequestParam String betRoundId,
                                         @RequestParam String userId) {
        return userBetRoundService.userBettingRoundResult(betRoundId, userId);
    }

    @OnlyUser
    @Operation(
            summary = "Gets  User bet  By  bet_round_id and user_id",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserBetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserBetRoundDto> getAllUserBetRound(@RequestParam String userId,
                                                    @RequestParam String betRoundId) {
        return userBetRoundService.getAllUserBetRound(userId, betRoundId);
    }

}
