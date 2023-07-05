package com.codependent.springadts.application.domain.error;

public final class DepartmentExistsError extends DomainError {
    public DepartmentExistsError() {
        super("department.exists");
    }
}
