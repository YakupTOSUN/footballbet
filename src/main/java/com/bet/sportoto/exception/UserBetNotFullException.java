package com.bet.sportoto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UserBetNotFullException extends RestException{
    @Getter
    private final HttpStatus httpStatus;

    public UserBetNotFullException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
