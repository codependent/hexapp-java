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
    public Either<? extends ApplicationError, Department> createDepartment(CreateDepartmentCommand command) {
        Either<? extends ApplicationError, Department> department = Department.create(command.id(), command.name());
        Either<? extends ApplicationError, Department> createdDepartment = department.flatMap((Department dep) -> {
            Optional<Department> existingDepartment = getDepartmentDrivenPort.getByName(command.name());
            if (existingDepartment.isPresent()) {
                Either<? extends ApplicationError, Department> left = Either.left(new DepartmentExistsError());
                return left;
            } else {
                Either<? extends ApplicationError, Department> right = createDepartmentDrivenPort.create(dep);
                return right;
            }
        });
        return createdDepartment;
    }
}
