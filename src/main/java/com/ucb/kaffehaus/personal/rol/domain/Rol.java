package com.ucb.kaffehaus.personal.rol.domain;

import java.util.UUID;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Rol extends AggregateRoot<UUID> {

    private String nombre;
    private boolean borrado;

    private Rol(UUID id, String nombre, boolean borrado) {
        super(id);
        this.nombre = nombre;
        this.borrado = borrado;
    }

    public static Rol create(String nombre) {
        return new Rol(null, nombre, false);
    }

    public static Rol restore(UUID id, String nombre, boolean borrado) {
        return new Rol(id, nombre, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
