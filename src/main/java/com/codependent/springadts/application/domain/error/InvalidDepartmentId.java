package com.codependent.springadts.application.domain.error;

public final class InvalidDepartmentId extends ValidationError {
    public InvalidDepartmentId() {
        super("department.id.invalid");
    }
}
