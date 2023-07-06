package com.codependent.hexapp.application.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartmentTests {

    @Test
    void shouldCopyDepartment() {
        Department department = new Department(1, "IT");
        Department department1 = department.withId(2);

        assertEquals(2, department1.id());

        Department department2 = department1.withName("HR");

        assertEquals("HR", department2.name());

    }
}
