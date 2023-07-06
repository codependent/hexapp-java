package com.codependent.hexapp.application.port.in.impl;

import com.codependent.hexapp.adapter.out.repository.DepartmentRepositoryInMemoryImpl;
import com.codependent.hexapp.adapter.out.repository.GetDepartmentDrivenPortImpl;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.domain.exception.DomainErrorException;
import com.codependent.hexapp.application.domain.exception.ValidationErrorsException;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateDepartmentUseCaseImplTests {

    @Test
    void shouldCreateDepartment() {

        final GetDepartmentDrivenPort getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(new DepartmentRepositoryInMemoryImpl());
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = department -> department;
        CreateDepartmentUseCase createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        Department department = createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(1, "name"));

        assertEquals(new Department(1, "name"), department);

    }

    @Test
    void shouldNotCreateExistingDepartment() {

        DepartmentRepositoryInMemoryImpl departmentRepositoryInMemory = new DepartmentRepositoryInMemoryImpl();
        departmentRepositoryInMemory.save(new Department(1, "name"));
        departmentRepositoryInMemory.save(new Department(2, "name2"));
        final GetDepartmentDrivenPort getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(departmentRepositoryInMemory);
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = department -> department;
        CreateDepartmentUseCase createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        DomainErrorException exception = assertThrows(DomainErrorException.class, () -> createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(2, "name2")));
        
        assertEquals(DepartmentExistsError.class, exception.getError().getClass());
        assertEquals("department.exists", exception.getError().getCode());
    }

    @Test
    void shouldNotCreateDepartmentWithValidationErrors() {

        final GetDepartmentDrivenPort getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(new DepartmentRepositoryInMemoryImpl());
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = department -> department;
        CreateDepartmentUseCase createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        ValidationErrorsException exception = assertThrows(ValidationErrorsException.class, () -> 
                createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(0, "")));
        
        assertEquals(2, exception.getValidationErrors().getErrors().size());
        assertTrue(exception.getValidationErrors().getErrors().stream().anyMatch(error -> error.getCode().equals("department.id.invalid")));
        assertTrue(exception.getValidationErrors().getErrors().stream().anyMatch(error -> error.getCode().equals("department.name.empty")));

    }
}
