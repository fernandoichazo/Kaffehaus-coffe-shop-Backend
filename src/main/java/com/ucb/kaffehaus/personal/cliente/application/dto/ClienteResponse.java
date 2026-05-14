package com.ucb.kaffehaus.personal.cliente.application.dto;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;

public class ClienteResponse {
    private Integer id;
    private Integer personaId;
    private boolean borrado;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Integer getPersonaId() {return personaId;}
    public void setPersonaId(Integer personaId) {this.personaId = personaId;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static ClienteResponse from(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        response.id = cliente.getId();
        response.personaId = cliente.getPersona() != null ? cliente.getPersona().getId() : null;
        response.borrado = cliente.isBorrado();
        return response;
    }
}
