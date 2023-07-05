package com.codependent.springadts.adapter.in.web;

import com.codependent.springadts.application.domain.Department;
import com.codependent.springadts.application.port.in.CreateDepartmentUseCase;
import com.codependent.springadts.application.port.in.dto.CreateDepartmentCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    private final CreateDepartmentUseCase departmentService;

    public DepartmentController(CreateDepartmentUseCase departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    Department create(@RequestBody CreateDepartmentCommand command) {
        return departmentService.createDepartment(command);
    }

}
