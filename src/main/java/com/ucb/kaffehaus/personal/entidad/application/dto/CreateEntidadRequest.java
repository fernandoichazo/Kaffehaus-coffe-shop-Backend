package com.ucb.kaffehaus.personal.entidad.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateEntidadRequest {
    private String nombre;
    private String nit;
    private String telefono;
    private String descripcion;

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNit() {return nit;}
    public void setNit(String nit) {this.nit = nit;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (nombre == null || nombre.trim().isEmpty()) {
            result.addError("nombre", "El nombre es obligatorio");
        } else if (nombre.length() < 2 || nombre.length() > 100) {
            result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
        }

        if (nit == null || nit.trim().isEmpty()) {
            result.addError("nit", "El NIT es obligatorio");
        } else if (nit.length() < 2 || nit.length() > 30) {
            result.addError("nit", "El NIT debe tener entre 2 y 30 caracteres");
        }

        if (telefono != null && !telefono.trim().isEmpty()) {
            if (telefono.length() < 2 || telefono.length() > 20) {
                result.addError("telefono", "El telefono debe tener entre 2 y 20 caracteres");
            }
        }

        if (descripcion != null && !descripcion.trim().isEmpty()) {
            if (descripcion.length() > 255) {
                result.addError("descripcion", "La descripcion no debe exceder 255 caracteres");
            }
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
