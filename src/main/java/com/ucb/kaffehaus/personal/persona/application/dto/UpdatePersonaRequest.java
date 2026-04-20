package com.ucb.kaffehaus.personal.persona.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class UpdatePersonaRequest {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (nombre != null) {
            String nombreLimpio = nombre.trim();
            if (nombreLimpio.length() < 2 || nombreLimpio.length() > 100) {
                result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
            }
        }

        if (apellidos != null) {
            String apellidosLimpios = apellidos.trim();
            if (apellidosLimpios.length() < 2 || apellidosLimpios.length() > 100) {
                result.addError("apellidos", "Los apellidos debe tener entre 2 y 100 caracteres");
            }
        }

        if (dni != null) {
            String dniLimpio = dni.trim();
            if (dniLimpio.length() < 2 || dniLimpio.length() > 10) {
                result.addError("dni", "el dni debe tener entre 2 y 10 caracteres");
            }
        }

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return result;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
}