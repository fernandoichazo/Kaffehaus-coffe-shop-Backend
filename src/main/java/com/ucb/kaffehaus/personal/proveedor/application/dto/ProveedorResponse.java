package com.ucb.kaffehaus.personal.proveedor.application.dto;

import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;

public class ProveedorResponse {
    private Integer id;
    private Integer personaId;
    private Integer entidadId;
    private boolean borrado;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Integer getPersonaId() {return personaId;}
    public void setPersonaId(Integer personaId) {this.personaId = personaId;}
    public Integer getEntidadId() {return entidadId;}
    public void setEntidadId(Integer entidadId) {this.entidadId = entidadId;}
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
