package com.ucb.kaffehaus.personal.entidad.application.dto;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;

public class EntidadResponse {
    private Integer id;
    private String nombre;
    private String nit;
    private String telefono;
    private String descripcion;
    private boolean borrado;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
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

    public static EntidadResponse from(Entidad entidad) {
        EntidadResponse response = new EntidadResponse();
        response.id = entidad.getId();
        response.nombre = entidad.getNombre();
        response.nit = entidad.getNit();
        response.telefono = entidad.getTelefono();
        response.descripcion = entidad.getDescripcion();
        response.borrado = entidad.isBorrado();
        return response;
    }
}
