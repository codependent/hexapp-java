package com.codependent.springadts.application.domain.exception;


import com.codependent.springadts.application.domain.error.DomainError;

public class DomainErrorException extends RuntimeException {

    private final DomainError error;

    public DomainErrorException(DomainError error) {
        this.error = error;
    }

    public DomainError getError() {
        return error;
    }
}
