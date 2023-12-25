package com.bet.sportoto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundPlannedBetRoundsException extends RestException{
    @Getter
    private final HttpStatus httpStatus;

    public NotFoundPlannedBetRoundsException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
