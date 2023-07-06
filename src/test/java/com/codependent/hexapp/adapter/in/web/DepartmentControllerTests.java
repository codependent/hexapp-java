package com.codependent.hexapp.adapter.in.web;

import com.codependent.hexapp.application.domain.Department;
import com.codependent.hexapp.application.port.in.CreateDepartmentUseCase;
import com.codependent.hexapp.application.port.in.dto.CreateDepartmentCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
}
