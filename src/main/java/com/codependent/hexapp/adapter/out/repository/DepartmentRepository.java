package com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import io.vavr.control.Either;

import java.util.Optional;

public interface DepartmentRepository {

    Either<ApplicationError, Optional<Department>> get(int id);
    Either<ApplicationError, Optional<Department>> get(String name);

    Either<ApplicationError, Department> save(Department department);
}
