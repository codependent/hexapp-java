package com.codependent.hexapp.application.domain.error;

public abstract sealed class DomainError implements ApplicationError permits DepartmentBlacklistedError, DepartmentExistsError {

    private final String code;

    protected DomainError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

