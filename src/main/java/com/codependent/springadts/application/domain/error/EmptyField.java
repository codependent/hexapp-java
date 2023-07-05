package com.codependent.springadts.application.domain.error;

public final class EmptyField extends ValidationError {

    public EmptyField(String entity, String field, String error) {
        super(entity, field, error);
    }
}
