package com.codependent.hexapp.adapter.in.web;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.ApplicationError;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    private final CreateDepartmentUseCase departmentService;

    public DepartmentController(CreateDepartmentUseCase departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateDepartmentCommand command) {
        Either<? extends ApplicationError, Department> department = departmentService.createDepartment(command);
        return ResponseHandler.toResponseEntity(CREATED, department);
        
    }

}
