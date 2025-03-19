package com.findit.FindIt.exception.handler.role;

import com.findit.FindIt.exception.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoleExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> roleNotFoundExceptionHandler(RoleNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
