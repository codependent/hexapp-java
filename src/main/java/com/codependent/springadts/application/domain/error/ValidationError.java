package com.codependent.springadts.application.domain.error;

public abstract sealed class ValidationError implements ApplicationError permits EmptyField, InvalidField {

    private final String entity;
    private final String field;
    private final String error;

    protected ValidationError(String entity, String field, String error) {
        this.entity = entity;
        this.field = field;
        this.error = error;
    }
    
    public String getCode() {
        return String.format("%s.%s.%s", entity, field, error);
    }
}
