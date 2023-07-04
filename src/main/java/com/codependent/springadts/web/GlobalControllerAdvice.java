package com.codependent.springadts.web;

import com.codependent.springadts.application.domain.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalControllerAdvice {
    
    @ExceptionHandler(ValidationErrorsException.class)
    @ResponseStatus(BAD_REQUEST)
    ValidationErrors validationException(ValidationErrorsException validationErrorsException) {
        return validationErrorsException.getErrors();
    }

    @ExceptionHandler(DomainErrorException.class)
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<DomainError> domainException(DomainErrorException domainErrorException) {
        DomainError error = domainErrorException.getError();
        return switch (error) {
            case DepartmentExistsError departmentExistsError -> ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            case DepartmentBlacklistedError departmentBlacklistedError -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        };
    }
}
