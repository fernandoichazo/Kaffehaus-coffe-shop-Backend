package com.ucb.kaffehaus.inventario.categoria.application.dto;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateCategoriaRequest {
    private String nombre;

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (nombre == null || nombre.trim().isEmpty()) {
            result.addError("nombre", "El nombre es obligatorio");
        } else if (nombre.length() < 2 || nombre.length() > 100) {
            result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
