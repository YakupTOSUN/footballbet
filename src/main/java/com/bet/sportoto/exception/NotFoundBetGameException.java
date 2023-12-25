package com.bet.sportoto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundBetGameException extends RestException{
    @Getter
    private final HttpStatus httpStatus;

    public NotFoundBetGameException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
