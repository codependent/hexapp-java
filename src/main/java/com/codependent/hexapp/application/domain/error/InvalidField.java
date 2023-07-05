package com.codependent.hexapp.application.domain.error;

public final class InvalidField extends ValidationError {

    public InvalidField(String entity, String field, String error) {
        super(entity, field, error);
    }
}
