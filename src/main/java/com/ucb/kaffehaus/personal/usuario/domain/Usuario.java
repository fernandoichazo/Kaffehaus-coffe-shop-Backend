package com.ucb.kaffehaus.personal.usuario.domain;

import java.util.UUID;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Usuario extends AggregateRoot<UUID> {

    private Persona persona;
    private Rol rol;
    private String correo;
    private String contrasena;

    private Usuario(UUID id, Persona persona, Rol rol, String correo, String contrasena) {
        super(id);
        this.persona = persona;
        this.rol = rol;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public static Usuario create(Persona persona, Rol rol, String correo, String contrasena) {
        return new Usuario(null, persona, rol, correo, contrasena);
    }

    public static Usuario restore(UUID id, Persona persona, Rol rol, String correo, String contrasena) {
        return new Usuario(id, persona, rol, correo, contrasena);
    }

    public Persona getPersona() {return persona;}
    public void setPersona(Persona persona) {this.persona = persona;}
    public Rol getRol() {return rol;}
    public void setRol(Rol rol) {this.rol = rol;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
}
