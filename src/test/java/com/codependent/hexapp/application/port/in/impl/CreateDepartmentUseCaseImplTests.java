package com.codependent.hexapp.application.port.in.impl;

import com.codependent.hexapp.adapter.out.repository.DepartmentRepositoryInMemoryImpl;
import com.codependent.hexapp.adapter.out.repository.GetDepartmentDrivenPortImpl;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.domain.error.ValidationErrors;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import io.vavr.control.Either;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateDepartmentUseCaseImplTests {

    @Test
    void shouldCreateDepartment() {

        val getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(new DepartmentRepositoryInMemoryImpl());
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = Either::right;
        CreateDepartmentUseCase createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        val department = createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(1, "name"));

        assertEquals(Department.create(1, "name"), department);

    }

    @Test
    void shouldNotCreateExistingDepartment() {

        val departmentRepositoryInMemory = new DepartmentRepositoryInMemoryImpl();
        departmentRepositoryInMemory.save(Department.create(1, "name").get());
        departmentRepositoryInMemory.save(Department.create(2, "name2").get());
        val getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(departmentRepositoryInMemory);
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = Either::right;
        val createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        Either<ApplicationError, Department> dep = createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(2, "name2"));
        switch (dep) {
            case Either.Left<ApplicationError, Department> l -> {
                assertEquals(DepartmentExistsError.class, l.getLeft().getClass());
                assertEquals("department.exists", ((DepartmentExistsError) l.getLeft()).getCode());
            }
            case Either.Right<ApplicationError, Department> r -> fail();
            default -> fail();
        }
    }

    @Test
    void shouldNotCreateDepartmentWithValidationErrors() {

        val getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(new DepartmentRepositoryInMemoryImpl());
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = Either::right;
        val createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        Either<ApplicationError, Department> dep = createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(0, ""));
        switch (dep) {
            case Either.Left<ApplicationError, Department> l -> {
                assertEquals(2, ((ValidationErrors) l.getLeft()).getErrors().size());
                assertTrue(((ValidationErrors) l.getLeft()).getErrors().stream().anyMatch(error -> error.getCode().equals("department.id.invalid")));
                assertTrue(((ValidationErrors) l.getLeft()).getErrors().stream().anyMatch(error -> error.getCode().equals("department.name.empty")));
            }
            case Either.Right<ApplicationError, Department> r -> fail();
            default -> fail();
        }
    }
}
