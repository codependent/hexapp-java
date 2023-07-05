package com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.out.GetDepartmentDrivenPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetDepartmentDrivenPortImpl implements GetDepartmentDrivenPort {

    private final DepartmentRepository departmentRepository;

    public GetDepartmentDrivenPortImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Department> getByName(String name) {
        return departmentRepository.get(name);
    }
}
