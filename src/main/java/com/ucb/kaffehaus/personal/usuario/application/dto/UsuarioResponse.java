package com.ucb.kaffehaus.personal.usuario.application.dto;

import com.ucb.kaffehaus.personal.usuario.domain.Usuario;

public class UsuarioResponse {
    private Integer id;
    private Integer personaId;
    private Integer rolId;
    private String correo;
    private String contrasena;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Integer getPersonaId() {return personaId;}
    public void setPersonaId(Integer personaId) {this.personaId = personaId;}
    public Integer getRolId() {return rolId;}
    public void setRolId(Integer rolId) {this.rolId = rolId;}
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
