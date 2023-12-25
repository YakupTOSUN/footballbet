package com.bet.sportoto.exception.handler;

import lombok.Data;

@Data
public class ErrorResponse {
    private String field;
    private String ExceptionClass;
    private String message;
}
