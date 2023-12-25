package com.bet.sportoto.game.controller.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class GamePostRequest {
    private String firstTeam;
    private String secondTeam;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime playDt;
}
