package com.codependent.hexapp.application.port.in.impl;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.domain.exception.DomainErrorException;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateDepartmentUseCaseImpl implements CreateDepartmentUseCase {
    
    private final CreateDepartmentDrivenPort createDepartmentDrivenPort;
    private final GetDepartmentDrivenPort getDepartmentDrivenPort;

    public CreateDepartmentUseCaseImpl(CreateDepartmentDrivenPort createDepartmentDrivenPort, GetDepartmentDrivenPort getDepartmentDrivenPort) {
        this.createDepartmentDrivenPort = createDepartmentDrivenPort;
        this.getDepartmentDrivenPort = getDepartmentDrivenPort;
    }

    @Override
    public Department createDepartment(CreateDepartmentCommand command) {
        val department = new Department(command.id(), command.name());
        Optional<Department> existingDepartment = getDepartmentDrivenPort.getByName(command.name());
        if(existingDepartment.isPresent()) {
            throw new DomainErrorException(new DepartmentExistsError());
        } else {
            return createDepartmentDrivenPort.create(department);
        }
    }
}