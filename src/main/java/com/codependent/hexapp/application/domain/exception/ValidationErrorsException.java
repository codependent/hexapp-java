package com.codependent.hexapp.application.domain.exception;


import com.codependent.hexapp.application.domain.error.ValidationErrors;

public class ValidationErrorsException extends RuntimeException {

    private final ValidationErrors validationErrors;

    public ValidationErrorsException(ValidationErrors validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }
}
