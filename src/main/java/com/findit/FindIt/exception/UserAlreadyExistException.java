package com.findit.FindIt.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String s) {
        super(s);
    }
}
