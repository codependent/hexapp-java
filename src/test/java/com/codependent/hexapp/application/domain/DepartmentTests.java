package com.codependent.hexapp.application.domain;

import com.codependent.hexapp.application.domain.error.ApplicationError;
import io.vavr.control.Either;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DepartmentTests {

    @Test
    void shouldCopyDepartment() {
        val department = Department.create(1, "IT");
        Either<ApplicationError, Department> department1 = department.map(it -> it.withId(2));

        switch (department1) {
            case Either.Left<ApplicationError, Department> l -> fail();
            case Either.Right<ApplicationError, Department> r -> assertEquals(2, r.get().getId());
            default -> fail();
        }

        Either<ApplicationError, Department> department2 = department1.map(it -> it.withName("HR"));

        switch (department2) {
            case Either.Left<ApplicationError, Department> l -> fail();
            case Either.Right<ApplicationError, Department> r -> assertEquals("HR", department2.get().getName());
            default -> fail();
        }

    }
}
