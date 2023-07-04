package com.codependent.springadts.application.domain.error;


public class DomainErrorException extends RuntimeException {

    private final DomainError error;

    public DomainErrorException(DomainError error) {
        this.error = error;
    }

    public DomainError getError() {
        return error;
    }
}
