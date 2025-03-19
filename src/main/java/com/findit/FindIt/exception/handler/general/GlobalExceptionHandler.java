package com.findit.FindIt.exception.handler.general;

import com.findit.FindIt.exception.BadAuthCredentialsException;
import com.findit.FindIt.exception.PasswordValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PasswordValidationException.class)
    public ResponseEntity<String> passwordValidationExceptionHandler(PasswordValidationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(BadAuthCredentialsException.class)
    public ResponseEntity<String> badAuthCredentialsExceptionHandler(BadAuthCredentialsException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
