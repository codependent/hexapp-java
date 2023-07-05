package com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.out.CreateDepartmentDrivenPort;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentDrivenPortImpl implements CreateDepartmentDrivenPort {
    
    private final DepartmentRepository departmentRepository;

    public CreateDepartmentDrivenPortImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }
}
