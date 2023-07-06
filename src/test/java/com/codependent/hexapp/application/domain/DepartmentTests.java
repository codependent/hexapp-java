package com.codependent.hexapp.application.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartmentTests {

    @Test
    void shouldCopyDepartment() {
        val department = new Department(1, "IT");
        val department1 = department.withId(2);

        assertEquals(2, department1.id());

        val department2 = department1.withName("HR");

        assertEquals("HR", department2.name());

    }
}
