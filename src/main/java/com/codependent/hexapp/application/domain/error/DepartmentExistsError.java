package com.codependent.hexapp.application.domain.error;

public final class DepartmentExistsError extends DomainError {
    public DepartmentExistsError() {
        super("department.exists");
    }
}
