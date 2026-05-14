package com.ucb.kaffehaus.personal.usuario.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class UpdateUsuarioRequest {
    private UUID personaId;
    private UUID rolId;
    private String correo;
    private String contrasena;

    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}
    public UUID getRolId() {return rolId;}
    public void setRolId(UUID rolId) {this.rolId = rolId;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (correo != null) {
            String correoLimpio = correo.trim();
            if (!correoLimpio.isEmpty() && !correoLimpio.contains("@")) {
                result.addError("correo", "El correo no es valido");
            } else if (correoLimpio.length() > 150) {
                result.addError("correo", "El correo no debe exceder 150 caracteres");
            }
        }

        if (contrasena != null) {
            String contrasenaLimpia = contrasena.trim();
            if (!contrasenaLimpia.isEmpty() && (contrasenaLimpia.length() < 6 || contrasenaLimpia.length() > 100)) {
                result.addError("contrasena", "La contrasena debe tener entre 6 y 100 caracteres");
            }
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
