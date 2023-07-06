package com.codependent.hexapp.adapter.in.web;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.domain.error.DepartmentBlacklistedError;
import com.codependent.hexapp.application.domain.error.DepartmentExistsError;
import com.codependent.hexapp.application.domain.error.InvalidField;
import com.codependent.hexapp.application.domain.error.ValidationErrors;
import com.codependent.hexapp.application.domain.exception.DomainErrorException;
import com.codependent.hexapp.application.domain.exception.ValidationErrorsException;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@TestConstructor(autowireMode = ALL)
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTests {

    private final MockMvc mvc;

    @MockBean
    private CreateDepartmentUseCase createDepartmentUseCase;

    public DepartmentControllerTests(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void shouldCreateDepartment() throws Exception {

        CreateDepartmentCommand createDepartmentCommand = new CreateDepartmentCommand(1, "name");
        when(createDepartmentUseCase.createDepartment(createDepartmentCommand))
                .thenReturn(new Department(createDepartmentCommand.id(), createDepartmentCommand.name()));
        
        mvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(new ObjectMapper().writeValueAsString(createDepartmentCommand))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(createDepartmentCommand.name())));
    }

    @Test
    void shouldFailCreatingInvalidDepartment() throws Exception {

        InvalidField invalidField = new InvalidField("department", "id", "invalid");
        InvalidField invalidField2 = new InvalidField("department", "name", "empty");

        CreateDepartmentCommand createDepartmentCommand = new CreateDepartmentCommand(0, "");
        when(createDepartmentUseCase.createDepartment(createDepartmentCommand))
                .thenThrow(new ValidationErrorsException(new ValidationErrors(List.of(
                        invalidField,
                        invalidField2))));

        mvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(new ObjectMapper().writeValueAsString(createDepartmentCommand))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
                
    }

    @Test
    void shouldFailCreatingExistingDepartment() throws Exception {
        
        val createDepartmentCommand = new CreateDepartmentCommand(0, "");
        when(createDepartmentUseCase.createDepartment(createDepartmentCommand))
                .thenThrow(new DomainErrorException(new DepartmentExistsError()));

        mvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(new ObjectMapper().writeValueAsString(createDepartmentCommand))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    @Test
    void shouldFailCreatingBlacklistedDepartment() throws Exception {

        val createDepartmentCommand = new CreateDepartmentCommand(0, "");
        when(createDepartmentUseCase.createDepartment(createDepartmentCommand))
                .thenThrow(new DomainErrorException(new DepartmentBlacklistedError()));

        mvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(new ObjectMapper().writeValueAsString(createDepartmentCommand))
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
}
