package com.codependent.springadts.application.domain;

import com.codependent.springadts.application.domain.error.InvalidDepartmentId;
import com.codependent.springadts.application.domain.error.InvalidDepartmentName;
import com.codependent.springadts.application.domain.error.ValidationErrors;
import com.codependent.springadts.application.domain.exception.ValidationErrorsException;

public record Department(int id, String name) {
    
    public Department {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id < 0) {
            validationErrors.add(new InvalidDepartmentId());
        }
        if(name == null) {
            validationErrors.add(new InvalidDepartmentName());
        }
        if(!validationErrors.isEmpty()) {
            throw new ValidationErrorsException(validationErrors);
        }
    }
}
