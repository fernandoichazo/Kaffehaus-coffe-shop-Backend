package com.ucb.kaffehaus.shared.application.dto;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private final Map<String, String> errors;
    
    public ValidationResult() {
        this.errors = new HashMap<>();
    }
    
    public void addError(String field, String message) {
        errors.put(field, message);
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public Map<String, String> getErrors() {
        return new HashMap<>(errors); // Retorna copia inmutable
    }
    
    public String getErrorMessage() {
        if (errors.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder("Errores de validación: ");
        errors.forEach((field, message) -> 
            sb.append(field).append(": ").append(message).append("; ")
        );
        return sb.toString();
    }
}
