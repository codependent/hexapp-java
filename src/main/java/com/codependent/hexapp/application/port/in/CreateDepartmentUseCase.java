package com.codependent.hexapp.application.port.in;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import io.vavr.control.Either;

public interface CreateDepartmentUseCase {
    
    Either<ApplicationError, Department> createDepartment(CreateDepartmentCommand command);
    
}
