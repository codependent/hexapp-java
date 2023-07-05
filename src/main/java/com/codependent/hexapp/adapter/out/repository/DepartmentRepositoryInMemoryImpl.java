package com.codependent.hexapp.adapter.out.repository;

import com.codependent.hexapp.application.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class DepartmentRepositoryInMemoryImpl implements DepartmentRepository{

    private final Map<Integer, Department> departmentMap = new HashMap<>();

    @Override
    public Optional<Department> get(int id) {
        return Optional.ofNullable(departmentMap.get(id));
    }

    @Override
    public Optional<Department> get(String name) {
        return departmentMap.values().stream()
                .filter(department -> department.name().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public Department save(Department department) {
        departmentMap.put(department.id(), department);
        return department;
    }
}
