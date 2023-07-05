package com.codependent.hexapp.application.domain.exception;


import com.codependent.hexapp.application.domain.error.DomainError;

public class DomainErrorException extends RuntimeException {

    private final DomainError error;

    public DomainErrorException(DomainError error) {
        this.error = error;
    }

    public DomainError getError() {
        return error;
    }
}
