package com.codependent.springadts.application.domain.error;

public final class DepartmentBlacklistedError extends DomainError {
    public DepartmentBlacklistedError() {
        super("department.blacklisted");
    }
}
