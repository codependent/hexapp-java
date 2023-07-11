package com.codependent.hexapp.com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.adapter.out.repository.DepartmentRepository;
import com.codependent.hexapp.adapter.out.repository.DepartmentRepositoryInMemoryImpl;
import com.codependent.hexapp.adapter.out.repository.GetDepartmentDrivenPortImpl;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GetDepartmentDrivenPortImplTests {
    
    private final DepartmentRepository departmentRepository = new DepartmentRepositoryInMemoryImpl();
    private final GetDepartmentDrivenPort getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(departmentRepository);
    
    @Test
    void shouldGetDepartment() {

        Either<ApplicationError, Department> department = Department.create(1, "IT");
        departmentRepository.save(department.get());

        Either<ApplicationError, Optional<Department>> dep = getDepartmentDrivenPort.getByName("IT");

        switch (dep) {
            case Either.Left<ApplicationError, Optional<Department>> l -> fail();
            case Either.Right<ApplicationError, Optional<Department>> r -> {
                assertTrue(r.get().isPresent());
                assertEquals(department.get(), r.get().get());
            }
            default -> fail();
        }
        
        dep = getDepartmentDrivenPort.get(1);
        
        switch (dep) {
            case Either.Left<ApplicationError, Optional<Department>> l -> fail();
            case Either.Right<ApplicationError, Optional<Department>> r -> {
                assertTrue(r.get().isPresent());
                assertEquals(department.get(), r.get().get());
            }
            default -> fail();
        }

    }

    @Test
    void shouldntGetDepartment() {
        
        Either<ApplicationError, Optional<Department>> dep = getDepartmentDrivenPort.getByName("FAKE");

        switch (dep) {
            case Either.Left<ApplicationError, Optional<Department>> l -> fail();
            case Either.Right<ApplicationError, Optional<Department>> r -> assertFalse(dep.get().isPresent());
            default -> fail();
        }
    }
    
}
