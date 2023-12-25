package com.bet.sportoto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundUserBetRoundException extends RestException{
    @Getter
    private final HttpStatus httpStatus;

    public NotFoundUserBetRoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
