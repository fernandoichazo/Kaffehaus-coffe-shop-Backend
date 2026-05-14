package com.ucb.kaffehaus.personal.usuario.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateUsuarioRequest {
    private UUID personaId;
    private UUID rolId;
    private String correo;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;

    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}
    public UUID getRolId() {return rolId;}
    public void setRolId(UUID rolId) {this.rolId = rolId;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (personaId == null) {
            if (nombre == null || nombre.trim().isEmpty()) {
                result.addError("nombre", "El nombre es obligatorio");
            } else if (nombre.length() < 2 || nombre.length() > 100) {
                result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
            }

            if (apellidos == null || apellidos.trim().isEmpty()) {
                result.addError("apellidos", "Los apellidos son necesarios");
            } else if (apellidos.length() < 2 || apellidos.length() > 100) {
                result.addError("apellidos", "Los apellidos deben tener entre 2 y 100 caracteres");
            }

            if (dni == null || dni.trim().isEmpty()) {
                result.addError("dni", "El dni es necesario");
            }
        }

        if (rolId == null) {
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
