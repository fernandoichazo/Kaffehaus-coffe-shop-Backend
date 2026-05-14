package com.ucb.kaffehaus.personal.persona.domain;

import java.util.UUID;

public class Persona{
    private UUID id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean borrado;

    private Persona(UUID id, String nombre, String apellidos, String telefono, String dni, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.borrado = borrado;
    }

    public static Persona create(String nombre, String apellidos, String telefono, String dni){
        return new Persona(null, nombre, apellidos, telefono, dni, false);
    }

    public static Persona restore(UUID id, String nombre, String apellidos, String telefono, String dni, boolean borrado){
        return new Persona(id, nombre, apellidos, telefono, dni, borrado);
    }


    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
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
}
