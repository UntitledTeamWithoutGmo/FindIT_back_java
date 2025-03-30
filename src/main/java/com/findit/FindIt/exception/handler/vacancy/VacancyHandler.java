package com.findit.FindIt.exception.handler.vacancy;

import com.findit.FindIt.exception.UserNotFoundException;
import com.findit.FindIt.exception.VacancyNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VacancyHandler {


    @ExceptionHandler(VacancyNotFound.class)
    public ResponseEntity<String> vacancyNotFoundExceptionHandler(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
