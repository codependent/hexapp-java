package com.codependent.hexapp.application.port.in.impl;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.domain.exception.DomainErrorException;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.codependent.hexapp.application.port.in.mapper.DepartmentMapper;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentUseCaseImpl implements CreateDepartmentUseCase {
    
    private final CreateDepartmentDrivenPort createDepartmentDrivenPort;
    private final GetDepartmentDrivenPort getDepartmentDrivenPort;
    
    private final DepartmentMapper departmentMapper;

    public CreateDepartmentUseCaseImpl(CreateDepartmentDrivenPort createDepartmentDrivenPort, GetDepartmentDrivenPort getDepartmentDrivenPort, DepartmentMapper departmentMapper) {
        this.createDepartmentDrivenPort = createDepartmentDrivenPort;
        this.getDepartmentDrivenPort = getDepartmentDrivenPort;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Department createDepartment(CreateDepartmentCommand command) {
        val department = departmentMapper.commandToDomain(command);
        val existingDepartment = getDepartmentDrivenPort.getByName(command.name());
        if(existingDepartment.isPresent() && existingDepartment.get().name().equalsIgnoreCase(command.name())) {
            throw new DomainErrorException(new DepartmentExistsError());
        } else {
            return createDepartmentDrivenPort.create(department);
        }
    }
}
