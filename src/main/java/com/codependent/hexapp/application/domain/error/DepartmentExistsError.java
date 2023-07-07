package com.codependent.hexapp.application.domain.error;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class DepartmentExistsError extends DomainError {
    
    private final String name;

    public DepartmentExistsError(String name) {
        super("department.exists");
        this.name = name;
    }
}
