package com.codependent.hexapp.application.port.out;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import io.vavr.control.Either;

import java.util.Optional;


public interface GetDepartmentDrivenPort {

    Either<ApplicationError, Optional<Department>> getByName(String name);

    Either<ApplicationError, Optional<Department>> get(int id);

}
