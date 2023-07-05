package com.codependent.hexapp.adapter.in.web;

import com.codependent.hexapp.application.domain.error.*;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
public class ResponseHandler {

    public static ResponseEntity<?> toResponseEntity(HttpStatus okStatus, Either<? extends ApplicationError, ?> either) {
        return either.fold(err -> {
            log.error("Error: {}", err);
            return switch (err) {
                case ValidationErrors validationErrors -> ResponseEntity.badRequest().body(validationErrors);
                case DepartmentBlacklistedError departmentBlacklistedError ->
                        buildErrorResponse(FORBIDDEN, departmentBlacklistedError);
                case DepartmentExistsError departmentExistsError -> buildErrorResponse(CONFLICT, departmentExistsError);
                case EmptyField emptyField -> ResponseEntity.badRequest().body(emptyField);
                case InvalidField invalidField -> ResponseEntity.badRequest().body(invalidField);
                case GenericError genericError -> ResponseEntity.internalServerError().build();
            };
        }, it -> ResponseEntity.status(okStatus).body(it));
    }

    private static ResponseEntity<ErrorsDto> buildErrorResponse(HttpStatus status, DomainError domainError) {
        return ResponseEntity.status(status).body(new ErrorsDto(List.of(domainError)));
    }

    record ErrorsDto(List<ApplicationError> errors) {
    }

}
