package com.codependent.springadts.application.domain.error;

public final class EmptyDepartmentName extends ValidationError {

    public EmptyDepartmentName() {
        super("department.name.empty");
    }
}
