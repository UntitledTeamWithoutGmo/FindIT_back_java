package com.findit.FindIt.exception;

public class BadAuthCredentialsException extends RuntimeException {
    public BadAuthCredentialsException(String message) {
        super(message);
    }
}
