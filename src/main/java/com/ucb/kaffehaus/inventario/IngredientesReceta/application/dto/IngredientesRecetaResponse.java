package com.ucb.kaffehaus.inventario.ingredientesReceta.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;

public class IngredientesRecetaResponse {
    private UUID id;
    private UUID recetaId;
    private UUID productoId;
    private BigDecimal cantidad;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getRecetaId() {return recetaId;}
    public void setRecetaId(UUID recetaId) {this.recetaId = recetaId;}
    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static IngredientesRecetaResponse from(IngredientesReceta ingredientes) {
        IngredientesRecetaResponse response = new IngredientesRecetaResponse();
        response.id = ingredientes.getId();
        response.recetaId = ingredientes.getRecetaId();
        response.productoId = ingredientes.getProductoId();
        response.cantidad = ingredientes.getCantidad();
        response.borrado = ingredientes.isBorrado();
        return response;
    }
}
