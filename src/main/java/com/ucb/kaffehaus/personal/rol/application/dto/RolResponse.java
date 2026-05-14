package com.ucb.kaffehaus.personal.rol.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.rol.domain.Rol;

public class RolResponse {
    private UUID id;
    private String nombre;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static RolResponse from(Rol rol) {
        RolResponse response = new RolResponse();
        response.id = rol.getId();
        response.nombre = rol.getNombre();
        response.borrado = rol.isBorrado();
        return response;
    }
}
