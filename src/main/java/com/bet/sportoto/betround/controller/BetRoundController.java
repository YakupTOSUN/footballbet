package com.bet.sportoto.betround.controller;

import com.bet.sportoto.betround.controller.request.PostBetRoundRequest;
import com.bet.sportoto.betround.entity.BetRound;
import com.bet.sportoto.betround.entity.BetRoundDto;
import com.bet.sportoto.betround.service.BetRoundService;
import com.bet.sportoto.exception.ErrorResponse;
import com.bet.sportoto.security.annotations.IsAuthentificated;
import com.bet.sportoto.security.annotations.OnlyAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/betrounds")
@RequiredArgsConstructor
public class BetRoundController {
    private final BetRoundService betRoundService;


    @OnlyAdmin
    @Operation(
            summary = "Adds a betting round (add bet round)",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BetRoundDto postBetRound(PostBetRoundRequest request) {
        return betRoundService.postBetRound(request);
    }

    @IsAuthentificated
    @Operation(
            summary = "Bet rounds getter bet status PLANNED",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/plannedbetrounds")
    @ResponseStatus(HttpStatus.OK)
    public Page<BetRoundDto> getPlannedBetRounds(Pageable pageable) {
        return betRoundService.getPlannedBetRounds(pageable);
    }

    @OnlyAdmin
    @Operation(
            summary = "Bet rounds getter bet status ENDED",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping("/endedbetrounds")
    @ResponseStatus(HttpStatus.OK)
    public Page<BetRoundDto> getEndedBetRounds(Pageable pageable) {
        return betRoundService.getEndedBetRounds(pageable);
    }

    @OnlyAdmin
    @Operation(
            summary = "Gett all bet rounds",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BetRoundDto> getAllBetRounds(Pageable pageable) {
        return betRoundService.getAllBetRounds(pageable);
    }

    @OnlyAdmin
    @Operation(
            summary = "Update status based on given bet round ID, New status PLANNED",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BetRoundDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            }
    )
    @PatchMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void statusChangePlanned(@RequestParam String betroundId){
       betRoundService.statusChangePlanned(betroundId);
    }

}
