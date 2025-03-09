package com.findit.FindIt.exception.handler.organization;

import com.findit.FindIt.exception.OrganizationAlreadyExistsException;
import com.findit.FindIt.exception.OrganizationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrganizationExceptionHandler {

    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseEntity<String> organizationNotFoundExceptionHandler(OrganizationNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(OrganizationAlreadyExistsException.class)
    public ResponseEntity<String> organizationAlreadyExistsExceptionHandler(OrganizationAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
