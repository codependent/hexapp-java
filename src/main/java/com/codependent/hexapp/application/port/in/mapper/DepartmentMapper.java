package com.codependent.hexapp.application.port.in.mapper;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface DepartmentMapper {

    //@Mapping(target = "withId", ignore = true) 
    //@Mapping(target = "withName", ignore = true)
    Department commandToDomain(CreateDepartmentCommand command);
    
}
