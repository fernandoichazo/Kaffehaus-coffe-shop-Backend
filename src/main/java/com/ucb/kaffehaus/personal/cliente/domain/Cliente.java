package com.ucb.kaffehaus.personal.cliente.domain;

import com.ucb.kaffehaus.personal.shared.domain.AggregateRoot;

public class Cliente extends AggregateRoot<Integer>{

    private boolean borrado;

    protected Cliente(Integer id) {
        super(id);
    }

    public void delete(){
        this.borrado = true;
    };

    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
