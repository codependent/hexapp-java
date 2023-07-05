package com.codependent.springadts.application.domain.error;

public abstract sealed class ValidationError implements ApplicationError permits EmptyDepartmentId, EmptyDepartmentName, InvalidDepartmentId, InvalidDepartmentName {
    
    private final String code;

    protected ValidationError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
