package com.ucb.kaffehaus.personal.cliente.domain;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Cliente extends AggregateRoot<Integer>{

    private boolean borrado;
    private Persona persona;

    private Cliente(Integer id, boolean borrado, Persona persona) {
        super(id);
        this.borrado = borrado;
        this.persona = persona;
    }

    public static Cliente create(Persona persona){
        return new Cliente(null, false, persona);
    }

    public static Cliente restore(Integer id, Persona persona, boolean borrado) {
        return new Cliente(id, borrado, persona);
    }

    public void delete(){
        this.borrado = true;
    };

    public Persona getPersona() {return persona;}
    public void setPersona(Persona persona) {this.persona = persona;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
