package com.ucb.kaffehaus.personal.entidad.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class UpdateEntidadRequest {
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

        if (nombre != null) {
            String nombreLimpio = nombre.trim();
            if (nombreLimpio.length() < 2 || nombreLimpio.length() > 100) {
                result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
            }
        }

        if (nit != null) {
            String nitLimpio = nit.trim();
            if (nitLimpio.length() < 2 || nitLimpio.length() > 30) {
                result.addError("nit", "El NIT debe tener entre 2 y 30 caracteres");
            }
        }

        if (telefono != null) {
            String telefonoLimpio = telefono.trim();
            if (!telefonoLimpio.isEmpty() && (telefonoLimpio.length() < 2 || telefonoLimpio.length() > 20)) {
                result.addError("telefono", "El telefono debe tener entre 2 y 20 caracteres");
            }
        }

        if (descripcion != null) {
            String descripcionLimpia = descripcion.trim();
            if (!descripcionLimpia.isEmpty() && descripcionLimpia.length() > 255) {
                result.addError("descripcion", "La descripcion no debe exceder 255 caracteres");
            }
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
