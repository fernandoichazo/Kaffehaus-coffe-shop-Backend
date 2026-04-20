package com.ucb.kaffehaus.personal.persona.application.dto;

import com.ucb.kaffehaus.personal.shared.application.dto.ValidationResult;

public class UpdatePersonaRequest {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (nombre != null && !nombre.isEmpty()) {
            if (nombre.length() < 2 || nombre.length() > 100) {
                result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
            }
        }

        if (apellidos != null && !apellidos.isEmpty()) {
            if (apellidos.length() < 2 || apellidos.length() > 100) {
                result.addError("apellidos", "Los apellidos debe tener entre 2 y 100 caracteres");
            }
        }

        if (dni != null && !dni.isEmpty()) {
            if (dni.length() < 2 || dni.length() > 10) {
                result.addError("dni", "el dni debe tener entre 2 y 10 caracteres");
            }
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