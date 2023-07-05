package com.codependent.springadts.web;

import com.codependent.springadts.application.domain.error.DepartmentBlacklistedError;
import com.codependent.springadts.application.domain.error.DepartmentExistsError;
import com.codependent.springadts.application.domain.error.DomainError;
import com.codependent.springadts.application.domain.error.ValidationErrors;
import com.codependent.springadts.application.domain.exception.DomainErrorException;
import com.codependent.springadts.application.domain.exception.ValidationErrorsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
    
    @ExceptionHandler(ValidationErrorsException.class)
    public ResponseEntity<ValidationErrors> validationException(ValidationErrorsException validationErrorsException) {
        return ResponseEntity.badRequest().body(validationErrorsException.getErrors());
    }

    @ExceptionHandler(DomainErrorException.class)
    public ResponseEntity<DomainError> domainException(DomainErrorException domainErrorException) {
        DomainError error = domainErrorException.getError();
        return switch (error) {
            case DepartmentExistsError departmentExistsError -> ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            case DepartmentBlacklistedError departmentBlacklistedError -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        };
    }
}
