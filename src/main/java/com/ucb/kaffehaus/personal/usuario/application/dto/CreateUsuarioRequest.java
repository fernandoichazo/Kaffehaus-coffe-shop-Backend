package com.ucb.kaffehaus.personal.usuario.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateUsuarioRequest {
    private Integer personaId;
    private Integer rolId;
    private String correo;
    private String contrasena;

    public Integer getPersonaId() {return personaId;}
    public void setPersonaId(Integer personaId) {this.personaId = personaId;}
    public Integer getRolId() {return rolId;}
    public void setRolId(Integer rolId) {this.rolId = rolId;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (personaId == null || personaId <= 0) {
            result.addError("personaId", "La persona es obligatoria");
        }

        if (rolId == null || rolId <= 0) {
            result.addError("rolId", "El rol es obligatorio");
        }

        if (correo == null || correo.trim().isEmpty()) {
            result.addError("correo", "El correo es obligatorio");
        } else if (!correo.contains("@")) {
            result.addError("correo", "El correo no es valido");
        } else if (correo.length() > 150) {
            result.addError("correo", "El correo no debe exceder 150 caracteres");
        }

        if (contrasena == null || contrasena.trim().isEmpty()) {
            result.addError("contrasena", "La contrasena es obligatoria");
        } else if (contrasena.length() < 6 || contrasena.length() > 100) {
            result.addError("contrasena", "La contrasena debe tener entre 6 y 100 caracteres");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
