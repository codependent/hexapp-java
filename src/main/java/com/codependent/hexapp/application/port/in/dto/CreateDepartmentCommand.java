package com.codependent.hexapp.application.port.in.dto;

import com.codependent.hexapp.application.domain.error.EmptyField;
import com.codependent.hexapp.application.domain.error.InvalidField;
import com.codependent.hexapp.application.domain.error.ValidationErrors;
import com.codependent.hexapp.application.domain.exception.ValidationErrorsException;
import org.apache.commons.lang3.StringUtils;

public record CreateDepartmentCommand(Integer id, String name) {

    public CreateDepartmentCommand {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id <= 0) {
            validationErrors.add(new InvalidField("department", "id", "invalid"));
        }
        if (StringUtils.isBlank(name)) {
            validationErrors.add(new EmptyField("department", "name", "empty"));
        }
        if (!validationErrors.getErrors().isEmpty()) {
            throw new ValidationErrorsException(validationErrors);
        }
    }

}
