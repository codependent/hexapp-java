package com.codependent.hexapp.application.domain.exception;


import com.codependent.hexapp.application.domain.error.ValidationErrors;

public class ValidationErrorsException extends RuntimeException {

    private final ValidationErrors errors;

    public ValidationErrorsException(ValidationErrors errors) {
        this.errors = errors;
    }

    public ValidationErrors getErrors() {
        return errors;
    }
}
