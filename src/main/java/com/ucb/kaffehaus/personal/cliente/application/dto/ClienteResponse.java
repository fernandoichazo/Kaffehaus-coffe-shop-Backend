package com.ucb.kaffehaus.personal.cliente.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;

public class ClienteResponse {
    private UUID id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean personaBorrado;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
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
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static ClienteResponse from(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();
        response.id = cliente.getId();
        if (cliente.getPersona() != null) {
            response.nombre = cliente.getPersona().getNombre();
            response.apellidos = cliente.getPersona().getApellidos();
            response.telefono = cliente.getPersona().getTelefono();
            response.dni = cliente.getPersona().getDni();
            response.personaBorrado = cliente.getPersona().isBorrado();
        }
        response.borrado = cliente.isBorrado();
        return response;
    }
}
