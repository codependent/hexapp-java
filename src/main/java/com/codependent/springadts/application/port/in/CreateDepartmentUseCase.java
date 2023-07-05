package com.codependent.springadts.application.port.in;

import com.codependent.springadts.application.domain.Department;
import com.codependent.springadts.application.port.in.dto.CreateDepartmentCommand;

public interface CreateDepartmentUseCase {
    
    Department createDepartment(CreateDepartmentCommand command);
    
    
}
