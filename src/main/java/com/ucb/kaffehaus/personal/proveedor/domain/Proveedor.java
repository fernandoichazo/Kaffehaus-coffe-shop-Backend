package com.ucb.kaffehaus.personal.proveedor.domain;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Proveedor extends AggregateRoot<Integer> {

    private Persona persona;
    private Entidad entidad;
    private boolean borrado;

    private Proveedor(Integer id, Persona persona, Entidad entidad, boolean borrado) {
        super(id);
        this.persona = persona;
        this.entidad = entidad;
        this.borrado = borrado;
    }

    public static Proveedor create(Persona persona, Entidad entidad) {
        return new Proveedor(null, persona, entidad, false);
    }

    public static Proveedor restore(Integer id, Persona persona, Entidad entidad, boolean borrado) {
        return new Proveedor(id, persona, entidad, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

    public Persona getPersona() {return persona;}
    public void setPersona(Persona persona) {this.persona = persona;}
    public Entidad getEntidad() {return entidad;}
    public void setEntidad(Entidad entidad) {this.entidad = entidad;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
