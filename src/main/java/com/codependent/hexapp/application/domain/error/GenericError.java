package com.codependent.hexapp.application.domain.error;

public record GenericError(Exception exception) implements ApplicationError {
}
