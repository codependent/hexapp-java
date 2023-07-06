package com.codependent.hexapp.application.port.out;

import com.codependent.hexapp.application.domain.Department;

import java.util.Optional;


public interface GetDepartmentDrivenPort {

    Optional<Department> get(int id);
    Optional<Department> getByName(String name);
}
