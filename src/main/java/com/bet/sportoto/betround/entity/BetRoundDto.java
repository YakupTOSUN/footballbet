package com.bet.sportoto.betround.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BetRoundDto {
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime playDt;
    private String title;
}
