package com.codependent.hexapp.adapter.in.http;

import com.codependent.hexapp.application.domain.error.*;
import com.codependent.hexapp.application.domain.exception.DomainErrorException;
import com.codependent.hexapp.application.domain.exception.ValidationErrorsException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ValidationErrorsException.class)
    public ResponseEntity<ValidationErrors> validationException(ValidationErrorsException validationErrorsException) {
        return ResponseEntity.badRequest().body(validationErrorsException.getValidationErrors());
    }

    @ExceptionHandler(DomainErrorException.class)
    public ResponseEntity<ErrorsDto> domainException(DomainErrorException domainErrorException) {
        val error = domainErrorException.getError();
        return switch (error) {
            case DepartmentExistsError departmentExistsError -> buildErrorResponse(CONFLICT, error);
            case DepartmentBlacklistedError departmentBlacklistedError -> buildErrorResponse(FORBIDDEN, error);
        };
    }

    private ResponseEntity<ErrorsDto> buildErrorResponse(HttpStatus status, DomainError domainError) {
        return ResponseEntity.status(status).body(new ErrorsDto(List.of(domainError)));
    }

    record ErrorsDto(List<ApplicationError> errors) {
    }
}
