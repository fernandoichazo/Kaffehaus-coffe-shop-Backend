package com.ucb.kaffehaus.personal.usuario.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.usuario.domain.Usuario;

public class UsuarioResponse {
    private UUID id;
    private UUID personaId;
    private UUID rolId;
    private String correo;
    private String contrasena;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getPersonaId() {return personaId;}
    public void setPersonaId(UUID personaId) {this.personaId = personaId;}
    public UUID getRolId() {return rolId;}
    public void setRolId(UUID rolId) {this.rolId = rolId;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public static UsuarioResponse from(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.id = usuario.getId();
        response.personaId = usuario.getPersona() != null ? usuario.getPersona().getId() : null;
        response.rolId = usuario.getRol() != null ? usuario.getRol().getId() : null;
        response.correo = usuario.getCorreo();
        response.contrasena = usuario.getContrasena();
        return response;
    }
}
