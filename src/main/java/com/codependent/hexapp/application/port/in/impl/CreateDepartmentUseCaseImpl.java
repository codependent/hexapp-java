package com.codependent.hexapp.application.port.in.impl;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import io.vavr.control.Either;
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
    public Either<ApplicationError, Department> createDepartment(CreateDepartmentCommand command) {
        Either<ApplicationError, Department> department = Department.create(command.id(), command.name());
        return department.flatMap(dep -> {
            Optional<Department> existingDepartment = getDepartmentDrivenPort.getByName(command.name());
            if (existingDepartment.isPresent()) {
                return Either.left(new DepartmentExistsError());
            } else {
                return createDepartmentDrivenPort.create(dep);
            }
        });
    }
}
