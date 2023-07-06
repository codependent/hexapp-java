package com.codependent.hexapp.application.port.out;

import com.codependent.hexapp.application.domain.Department;

@FunctionalInterface
public interface CreateDepartmentDrivenPort {
    
    Department create(Department department);
    
}
