package com.findit.FindIt.exception.handler.recruiter;

import com.findit.FindIt.exception.RecruiterAlreadyExistsException;
import com.findit.FindIt.exception.RecruiterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecruiterExceptionHandler {

    @ExceptionHandler(RecruiterNotFoundException.class)
    public ResponseEntity<String> recruiterNotFoundExceptionHandler(RecruiterNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(RecruiterAlreadyExistsException.class)
    public ResponseEntity<String> recruiterAlreadyExistsExceptionHandler(RecruiterAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
