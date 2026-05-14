package com.ucb.kaffehaus.personal.proveedor.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class UpdateProveedorRequest {
    private UUID personaId;
    private UUID entidadId;

    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}
    public UUID getEntidadId() {return entidadId;}
    public void setEntidadId(UUID entidadId) {this.entidadId = entidadId;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
