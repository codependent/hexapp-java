package com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.application.domain.Department;

import java.util.Optional;

public interface DepartmentRepository {
    
    Optional<Department> get(int id);
    Optional<Department> get(String name);
    
    Department save(Department department);
}
