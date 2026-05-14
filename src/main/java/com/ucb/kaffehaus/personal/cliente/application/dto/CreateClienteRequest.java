package com.ucb.kaffehaus.personal.cliente.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateClienteRequest {
    private Integer personaId;

    public Integer getPersonaId() {return personaId;}
    public void setPersonaId(Integer personaId) {this.personaId = personaId;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (personaId == null || personaId <= 0) {
            result.addError("personaId", "La persona es obligatoria");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
