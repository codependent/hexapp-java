package com.codependent.hexapp.application.domain.error;

import java.util.ArrayList;
import java.util.List;

public final class ValidationErrors implements ApplicationError {
    
    private final List<ValidationError> errors;

    public ValidationErrors() {
        this.errors = new ArrayList<>();
    }
    
    public ValidationErrors(List<ValidationError> errors) {
        this.errors = errors;
    }


    public void add(ValidationError error) {
        errors.add(error);
    }

    public List<ValidationError> getErrors() {
        return List.of(errors.toArray(new ValidationError[0]));
    }
}
