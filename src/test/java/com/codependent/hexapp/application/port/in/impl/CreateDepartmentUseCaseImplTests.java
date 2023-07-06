package com.codependent.hexapp.application.port.in.impl;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.domain.exception.DomainErrorException;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateDepartmentUseCaseImplTests {

    @Test
    void shouldCreateDepartment() {

        final GetDepartmentDrivenPort getDepartmentDrivenPort = name -> Optional.empty();
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = department -> department;

        CreateDepartmentUseCase createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        Department department = createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(1, "name"));

        assertEquals(new Department(1, "name"), department);

    }

    @Test
    void shouldNotCreateExistingDepartment() {

        final GetDepartmentDrivenPort getDepartmentDrivenPort = name -> Optional.of(new Department(1, "name"));
        final CreateDepartmentDrivenPort createDepartmentDrivenPort = department -> department;

        CreateDepartmentUseCase createDepartmentUseCase = new CreateDepartmentUseCaseImpl(createDepartmentDrivenPort, getDepartmentDrivenPort);

        DomainErrorException exception = assertThrows(DomainErrorException.class, () -> {
            createDepartmentUseCase.createDepartment(new CreateDepartmentCommand(1, "name"));
        });
        assertEquals(DepartmentExistsError.class, exception.getError().getClass());
    }
}
