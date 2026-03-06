package com.ucb.kaffehaus.persona.domain;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Persona extends AggregateRoot<Integer>{
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean borrado;

    private Persona(Integer id, String nombre, String apellidos, String telefono, String dni, boolean borrado) {
        super(id);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.borrado = borrado;
    }

    public static Persona create(String nombre, String apellidos, String telefono, String dni){
        return new Persona(null, nombre, apellidos, telefono, dni, false);
    }

    public static Persona restore(int id, String nombre, String apellidos, String telefono, String dni, boolean borrado){
        return new Persona(id, nombre, apellidos, telefono, dni, borrado);
    }

    
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
