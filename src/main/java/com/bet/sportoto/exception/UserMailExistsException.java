package com.bet.sportoto.exception;

public class UserMailExistsException extends RestException{
    public UserMailExistsException(String message){
        super(message);
    }
}
