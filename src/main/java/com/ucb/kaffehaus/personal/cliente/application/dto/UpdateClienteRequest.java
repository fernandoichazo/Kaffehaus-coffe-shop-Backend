package com.ucb.kaffehaus.personal.cliente.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class UpdateClienteRequest {
    private UUID personaId;

    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
