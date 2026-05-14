package com.ucb.kaffehaus.personal.cliente.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;

public class ClienteResponse {
    private UUID id;
    private UUID personaId;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}
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
