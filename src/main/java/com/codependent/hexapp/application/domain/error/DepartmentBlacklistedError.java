package com.codependent.hexapp.application.domain.error;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class DepartmentBlacklistedError extends DomainError {

    private final String name;
    
    public DepartmentBlacklistedError(String name) {
        super("department.exists");
        this.name = name;
    }
}