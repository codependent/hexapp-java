package com.codependent.hexapp.adapter.in.web;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    private final CreateDepartmentUseCase departmentService;

    public DepartmentController(CreateDepartmentUseCase departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    Department create(@RequestBody CreateDepartmentCommand command) {
        return departmentService.createDepartment(command);
    }

}
