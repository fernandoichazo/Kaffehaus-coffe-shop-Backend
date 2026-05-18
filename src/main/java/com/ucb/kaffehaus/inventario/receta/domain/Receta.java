package com.ucb.kaffehaus.inventario.receta.domain;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Receta extends AggregateRoot<UUID> {

    private UUID productoId;
    private BigDecimal cantidad;
    private boolean borrado;

    private Receta(UUID id, UUID productoId, BigDecimal cantidad, boolean borrado) {
        super(id);
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.borrado = borrado;
    }

    public static Receta create(UUID productoId, BigDecimal cantidad) {
        return new Receta(null, productoId, cantidad, false);
    }

    public static Receta restore(UUID id, UUID productoId, BigDecimal cantidad, boolean borrado) {
        return new Receta(id, productoId, cantidad, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
