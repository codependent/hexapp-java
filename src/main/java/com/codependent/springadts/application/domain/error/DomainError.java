package com.codependent.springadts.application.domain.error;

public abstract sealed class DomainError implements ApplicationError permits DepartmentBlacklistedError, DepartmentExistsError {

}

