package com.codependent.hexapp.application.port.in.mapper;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DepartmentMapper {

    Department commandToDomain(CreateDepartmentCommand command);
    
}
