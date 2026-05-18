package com.ucb.kaffehaus.inventario.categoria.domain;

import java.util.UUID;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Categoria extends AggregateRoot<UUID> {

    private String nombre;
    private boolean borrado;

    private Categoria(UUID id, String nombre, boolean borrado) {
        super(id);
        this.nombre = nombre;
        this.borrado = borrado;
    }

    public static Categoria create(String nombre) {
        return new Categoria(null, nombre, false);
    }

    public static Categoria restore(UUID id, String nombre, boolean borrado) {
        return new Categoria(id, nombre, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
