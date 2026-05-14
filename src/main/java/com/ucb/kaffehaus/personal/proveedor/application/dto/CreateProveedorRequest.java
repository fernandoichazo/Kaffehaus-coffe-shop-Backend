package com.ucb.kaffehaus.personal.proveedor.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateProveedorRequest {
    private Integer personaId;
    private Integer entidadId;

    public Integer getPersonaId() {return personaId;}
    public void setPersonaId(Integer personaId) {this.personaId = personaId;}
    public Integer getEntidadId() {return entidadId;}
    public void setEntidadId(Integer entidadId) {this.entidadId = entidadId;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (personaId == null || personaId <= 0) {
            result.addError("personaId", "La persona es obligatoria");
        }

        if (entidadId != null && entidadId <= 0) {
            result.addError("entidadId", "La entidad no es valida");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
