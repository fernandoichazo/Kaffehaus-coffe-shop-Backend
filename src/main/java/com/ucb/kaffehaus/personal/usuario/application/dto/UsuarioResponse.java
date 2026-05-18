package com.ucb.kaffehaus.personal.usuario.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.usuario.domain.Usuario;

public class UsuarioResponse {
    private UUID id;
    private UUID rolId;
    private String correo;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean personaBorrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getRolId() {return rolId;}
    public void setRolId(UUID rolId) {this.rolId = rolId;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
    public boolean isPersonaBorrado() {return personaBorrado;}
    public void setPersonaBorrado(boolean personaBorrado) {this.personaBorrado = personaBorrado;}

    public static UsuarioResponse from(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.id = usuario.getId();
        if (usuario.getPersona() != null) {
            response.nombre = usuario.getPersona().getNombre();
            response.apellidos = usuario.getPersona().getApellidos();
            response.telefono = usuario.getPersona().getTelefono();
            response.dni = usuario.getPersona().getDni();
            response.personaBorrado = usuario.getPersona().isBorrado();
        }
        response.rolId = usuario.getRol() != null ? usuario.getRol().getId() : null;
        response.correo = usuario.getCorreo();
        response.contrasena = usuario.getContrasena();
        return response;
    }
}
