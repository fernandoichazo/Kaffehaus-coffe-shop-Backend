package com.ucb.kaffehaus.personal.proveedor.application.dto;

import java.util.UUID;

import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;

public class ProveedorResponse {
    private UUID id;
    private UUID entidadId;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private boolean personaBorrado;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getEntidadId() {return entidadId;}
    public void setEntidadId(UUID entidadId) {this.entidadId = entidadId;}
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

    public static ProveedorResponse from(Proveedor proveedor) {
        ProveedorResponse response = new ProveedorResponse();
        response.id = proveedor.getId();
        if (proveedor.getPersona() != null) {
            response.nombre = proveedor.getPersona().getNombre();
            response.apellidos = proveedor.getPersona().getApellidos();
            response.telefono = proveedor.getPersona().getTelefono();
            response.dni = proveedor.getPersona().getDni();
            response.personaBorrado = proveedor.getPersona().isBorrado();
        }
        response.entidadId = proveedor.getEntidad() != null ? proveedor.getEntidad().getId() : null;
        response.borrado = proveedor.isBorrado();
        return response;
    }
}
