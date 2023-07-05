package com.codependent.springadts.web;

import com.codependent.springadts.application.domain.Department;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @PostMapping
    Department create() {
        Department department = new Department(1, "My Department");
        return department.withId(0);
    }

}
