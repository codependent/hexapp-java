package com.codependent.springadts.application.domain.error;

public final class EmptyDepartmentId extends ValidationError {
    public EmptyDepartmentId() {
        super("department.id.empty");
    }
}
