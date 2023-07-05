package com.codependent.springadts.application.domain.error;

public sealed interface ApplicationError permits DomainError, ValidationError, ValidationErrors {
    
}

