package com.codependent.hexapp.com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.adapter.out.repository.CreateDepartmentDrivenPortImpl;
import com.codependent.hexapp.adapter.out.repository.DepartmentRepository;
import com.codependent.hexapp.adapter.out.repository.DepartmentRepositoryInMemoryImpl;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateDepartmentDrivenPortImplTests {
    
    private final DepartmentRepository departmentRepository = new DepartmentRepositoryInMemoryImpl();
    private final CreateDepartmentDrivenPort createDepartmentDrivenPort = new CreateDepartmentDrivenPortImpl(departmentRepository);
    
    @Test
    void shouldCreateDepartment() {

        Department department = new Department(1, "IT");
        Department createdDepartment = createDepartmentDrivenPort.create(department);
        
        assertEquals(department, createdDepartment);
        

    }
    
}
