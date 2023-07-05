package com.codependent.hexapp.application.domain.error;

public sealed interface ApplicationError permits DomainError, ValidationError, ValidationErrors {
    
}

