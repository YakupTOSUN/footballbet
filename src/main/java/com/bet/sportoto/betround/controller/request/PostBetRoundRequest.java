package com.bet.sportoto.betround.controller.request;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class PostBetRoundRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime playDt;
    private String title;
}
