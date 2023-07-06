package com.codependent.hexapp.com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.adapter.out.repository.DepartmentRepository;
import com.codependent.hexapp.adapter.out.repository.DepartmentRepositoryInMemoryImpl;
import com.codependent.hexapp.adapter.out.repository.GetDepartmentDrivenPortImpl;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GetDepartmentDrivenPortImplTests {
    
    private final DepartmentRepository departmentRepository = new DepartmentRepositoryInMemoryImpl();
    private final GetDepartmentDrivenPort getDepartmentDrivenPort = new GetDepartmentDrivenPortImpl(departmentRepository);
    
    @Test
    void shouldGetDepartment() {

        Department department = new Department(1, "IT");
        departmentRepository.save(department);

        Optional<Department> dep = getDepartmentDrivenPort.getByName("IT");

        assertTrue(dep.isPresent());
        assertEquals(department, dep.get());

        dep = getDepartmentDrivenPort.get(1);

        assertTrue(dep.isPresent());
        assertEquals(department, dep.get());

    }

    @Test
    void shouldntGetDepartment() {
        
        Optional<Department> dep = getDepartmentDrivenPort.getByName("FAKE");
        assertFalse(dep.isPresent());

    }
    
}
