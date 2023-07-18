package com.codependent.hexapp.adapter.in.http.mapper;

import com.codependent.hexapp.adapter.in.http.CreateDepartmentRequest;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface DepartmentRequestMapper {

    //@Mapping(target = "withId", ignore = true) 
    //@Mapping(target = "withName", ignore = true)
    CreateDepartmentCommand requestToCommand(CreateDepartmentRequest requet);
    
}
