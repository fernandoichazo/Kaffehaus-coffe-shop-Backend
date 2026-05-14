package com.ucb.kaffehaus.personal.proveedor.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;

public class ProveedorResponse {
    private UUID id;
    private UUID personaId;
    private UUID entidadId;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}
    public UUID getEntidadId() {return entidadId;}
    public void setEntidadId(UUID entidadId) {this.entidadId = entidadId;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static ProveedorResponse from(Proveedor proveedor) {
        ProveedorResponse response = new ProveedorResponse();
        response.id = proveedor.getId();
        response.personaId = proveedor.getPersona() != null ? proveedor.getPersona().getId() : null;
        response.entidadId = proveedor.getEntidad() != null ? proveedor.getEntidad().getId() : null;
        response.borrado = proveedor.isBorrado();
        return response;
    }
}
