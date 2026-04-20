package com.ucb.kaffehaus.personal.entidad.domain;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Entidad extends AggregateRoot<Integer> {

    private String nombre;
    private String nit;
    private String telefono;
    private String descripcion;
    private boolean borrado;


    private Entidad(
        Integer id, 
        String nombre, 
        String nit,
        String telefono,
        String descripcion,
        boolean borrado
    ) {
        super(id);
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.borrado = borrado;
    }

    public static Entidad create(
        int id,
        String nombre,
        String nit,
        String telefono,
        String descripcion
    ){
        return new Entidad(id, nombre, nit, telefono, descripcion, false);
    }


    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNit() {return nit;}
    public void setNit(String nit) {this.nit = nit;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
