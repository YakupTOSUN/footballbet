package com.bet.sportoto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundUserBetException extends RestException{
    @Getter
    private final HttpStatus httpStatus;

    public NotFoundUserBetException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
