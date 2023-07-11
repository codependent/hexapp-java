package com.codependent.hexapp.com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.adapter.out.repository.CreateDepartmentDrivenPortImpl;
import com.codependent.hexapp.adapter.out.repository.DepartmentRepository;
import com.codependent.hexapp.adapter.out.repository.DepartmentRepositoryInMemoryImpl;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateDepartmentDrivenPortImplTests {
    
    private final DepartmentRepository departmentRepository = new DepartmentRepositoryInMemoryImpl();
    private final CreateDepartmentDrivenPort createDepartmentDrivenPort = new CreateDepartmentDrivenPortImpl(departmentRepository);
    
    @Test
    void shouldCreateDepartment() {

        Either<ApplicationError, Department> department = Department.create(1, "IT");
        Either<ApplicationError, Department> createdDepartment = createDepartmentDrivenPort.create(department.get());

        assertEquals(department, createdDepartment);
        

    }
    
}
