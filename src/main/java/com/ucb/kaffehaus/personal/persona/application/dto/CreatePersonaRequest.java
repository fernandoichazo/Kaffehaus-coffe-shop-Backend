package com.ucb.kaffehaus.personal.persona.application.dto;

import com.ucb.kaffehaus.personal.shared.application.dto.ValidationResult;

public class CreatePersonaRequest {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean borrado;


    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();
        
        if (nombre == null || nombre.trim().isEmpty()) {
            result.addError("nombre", "El nombre es obligatorio");
        } else if (nombre.length() < 2 || nombre.length() > 100) {
            result.addError("nombre", "El nombre debe tener entre 2 y 100 caracteres");
        }
        
        if (apellidos == null || apellidos.trim().isEmpty()){
            result.addError("apellidos", "Los apellidos son necesarios");
        } else if(apellidos.length() < 2 || apellidos.length() > 100){
            result.addError("apellidos", "Los apellidos deben tener entre 20 y 60 caracteres");
        }

        if (dni == null || dni.trim().isEmpty()){
            result.addError("dni", "El dni es necesario");
        }
        
        return result;
    }
}
