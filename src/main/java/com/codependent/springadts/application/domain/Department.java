package com.codependent.springadts.application.domain;

import com.codependent.springadts.application.domain.error.EmptyField;
import com.codependent.springadts.application.domain.error.InvalidField;
import com.codependent.springadts.application.domain.error.ValidationErrors;
import com.codependent.springadts.application.domain.exception.ValidationErrorsException;

public record Department(int id, String name) {
    
    public Department {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id < 0) {
            validationErrors.add(new InvalidField("department", "id", "invalid"));
        }
        if(name == null) {
            validationErrors.add(new EmptyField("department", "name", "empty"));
        }
        if(!validationErrors.getErrors().isEmpty()) {
            throw new ValidationErrorsException(validationErrors);
        }
    }
}
