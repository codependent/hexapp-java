package com.codependent.hexapp.application.port.out;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import io.vavr.control.Either;

@FunctionalInterface
public interface CreateDepartmentDrivenPort {
    
    Either<ApplicationError, Department> create(Department department);
    
}
