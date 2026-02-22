package com.ucb.kaffehaus.persona.application.dto;

import com.ucb.kaffehaus.persona.domain.Persona;

public class PersonaResponse {
    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean borrado;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
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

    public static PersonaResponse from(Persona persona){
        PersonaResponse personaResponse = new PersonaResponse();
        personaResponse.id = persona.getId();
        personaResponse.nombre = persona.getNombre();
        personaResponse.apellidos = persona.getApellidos();
        personaResponse.telefono = persona.getTelefono();
        personaResponse.dni = persona.getDni();
        personaResponse.borrado = persona.isBorrado();
        return personaResponse;
    }
}
