package com.codependent.springadts.application.domain.error;

public final class InvalidDepartmentName extends ValidationError {

    public InvalidDepartmentName() {
        super("department.name.invalid");
    }
}
