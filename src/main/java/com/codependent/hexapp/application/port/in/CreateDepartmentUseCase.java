package com.codependent.hexapp.application.port.in;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;

public interface CreateDepartmentUseCase {
    
    Department createDepartment(CreateDepartmentCommand command);
    
    
}
