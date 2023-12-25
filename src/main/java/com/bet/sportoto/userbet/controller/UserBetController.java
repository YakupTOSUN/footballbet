package com.bet.sportoto.userbet.controller;

import com.bet.sportoto.exception.ErrorResponse;
import com.bet.sportoto.security.annotations.OnlyUser;
import com.bet.sportoto.userbet.entity.Selection;
import com.bet.sportoto.userbet.entity.UserBetDto;
import com.bet.sportoto.userbet.service.UserBetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userbets")
@RequiredArgsConstructor
public class UserBetController {
    private final UserBetService userBetService;

    @OnlyUser
    @Operation(
            summary = "User adds user bet by getting user bet round ID",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserBetDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBetDto postUserBet(@RequestParam Selection selection, @RequestParam String userBetRoundId){
        return userBetService.postUserBet(selection, userBetRoundId);
    }
}
