package com.codependent.hexapp.application.domain;

import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.domain.error.EmptyField;
import com.codependent.hexapp.application.domain.error.InvalidField;
import com.codependent.hexapp.application.domain.error.ValidationErrors;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;
import org.apache.commons.lang3.StringUtils;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
@Getter
public @With class Department {

    private final int id;
    private final String name;
    
    public static Either<ApplicationError, Department> create(int id, String name) {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id <= 0) {
            validationErrors.add(new InvalidField("department", "id", "invalid"));
        }
        if(StringUtils.isBlank(name)) {
            validationErrors.add(new EmptyField("department", "name", "empty"));
        }
        if(validationErrors.getErrors().isEmpty()) {
            return Either.right(new Department(id, name));
        }
        return Either.left(validationErrors);
    }
}
