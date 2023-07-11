package com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.domain.error.GenericError;
import io.vavr.control.Either;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class DepartmentRepositoryInMemoryImpl implements DepartmentRepository {

    private final Map<Integer, Department> departmentMap = new HashMap<>();

    @Override
    public Either<ApplicationError, Optional<Department>> get(int id) {
        try {
            return Either.right(Optional.ofNullable(departmentMap.get(id)));
        } catch (Exception e) {
            return Either.left(new GenericError(e));
        }
    }

    @Override
    public Either<ApplicationError, Optional<Department>> get(String name) {
        try {
            return Either.right(departmentMap.values().stream()
                    .filter(department -> department.getName().equalsIgnoreCase(name)).findFirst());
        } catch (Exception e) {
            return Either.left(new GenericError(e));
        }
    }

    @Override
    public Either<ApplicationError, Department> save(Department department) {
        try {
            departmentMap.put(department.getId(), department);
            return Either.right(department);
        } catch (Exception e) {
            return Either.left(new GenericError(e));
        }
    }
}
