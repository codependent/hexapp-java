package com.codependent.springadts.application.port.in.impl;

import com.codependent.springadts.application.domain.Department;
import com.codependent.springadts.application.port.in.CreateDepartmentUseCase;
import com.codependent.springadts.application.port.in.dto.CreateDepartmentCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentUseCaseImpl implements CreateDepartmentUseCase {
    @Override
    public Department createDepartment(CreateDepartmentCommand command) {
        return new Department(command.id(), command.name());
    }
}
