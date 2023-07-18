package com.codependent.hexapp.adapter.in.http;

import com.codependent.hexapp.adapter.in.http.mapper.DepartmentRequestMapper;
import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    private final DepartmentRequestMapper requestMapper;
    private final CreateDepartmentUseCase departmentService;

    public DepartmentController(DepartmentRequestMapper requestMapper, CreateDepartmentUseCase departmentService) {
        this.requestMapper = requestMapper;
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    Department create(@RequestBody CreateDepartmentRequest request) {
        return departmentService.createDepartment(requestMapper.requestToCommand(request));
    }

}
