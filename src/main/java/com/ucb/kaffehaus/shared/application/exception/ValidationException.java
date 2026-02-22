package com.ucb.kaffehaus.shared.application.exception;

import java.util.Map;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;

public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public ValidationException(ValidationResult validationResult) {
        super(validationResult.getErrorMessage());
        this.errors = validationResult.getErrors();
    }

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
