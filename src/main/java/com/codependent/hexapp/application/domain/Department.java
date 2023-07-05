package com.codependent.hexapp.application.domain;

import com.codependent.hexapp.application.domain.error.EmptyField;
import com.codependent.hexapp.application.domain.error.InvalidField;
import com.codependent.hexapp.application.domain.error.ValidationErrors;
import com.codependent.hexapp.application.domain.exception.ValidationErrorsException;
import lombok.With;
import org.apache.commons.lang3.StringUtils;

public @With record Department(int id, String name) {
    
    public Department {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id <= 0) {
            validationErrors.add(new InvalidField("department", "id", "invalid"));
        }
        if(StringUtils.isBlank(name)) {
            validationErrors.add(new EmptyField("department", "name", "empty"));
        }
        if(!validationErrors.getErrors().isEmpty()) {
            throw new ValidationErrorsException(validationErrors);
        }
    }
}
