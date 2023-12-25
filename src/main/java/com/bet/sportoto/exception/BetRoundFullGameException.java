package com.bet.sportoto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BetRoundFullGameException extends RestException{
    @Getter
    private final HttpStatus httpStatus;

    public BetRoundFullGameException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
