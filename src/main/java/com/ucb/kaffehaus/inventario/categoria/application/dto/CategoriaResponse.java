package com.ucb.kaffehaus.inventario.categoria.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;

public class CategoriaResponse {
    private UUID id;
    private String nombre;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static CategoriaResponse from(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.id = categoria.getId();
        response.nombre = categoria.getNombre();
        response.borrado = categoria.isBorrado();
        return response;
    }
}
