package com.ucb.kaffehaus.inventario.ingredientesReceta.domain;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class IngredientesReceta extends AggregateRoot<UUID> {

    private UUID recetaId;
    private UUID productoId;
    private BigDecimal cantidad;
    private boolean borrado;

    private IngredientesReceta(UUID id, UUID recetaId, UUID productoId, BigDecimal cantidad, boolean borrado) {
        super(id);
        this.recetaId = recetaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.borrado = borrado;
    }

    public static IngredientesReceta create(UUID recetaId, UUID productoId, BigDecimal cantidad) {
        return new IngredientesReceta(null, recetaId, productoId, cantidad, false);
    }

    public static IngredientesReceta restore(UUID id, UUID recetaId, UUID productoId, BigDecimal cantidad, boolean borrado) {
        return new IngredientesReceta(id, recetaId, productoId, cantidad, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

    public UUID getRecetaId() {return recetaId;}
    public void setRecetaId(UUID recetaId) {this.recetaId = recetaId;}
    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
