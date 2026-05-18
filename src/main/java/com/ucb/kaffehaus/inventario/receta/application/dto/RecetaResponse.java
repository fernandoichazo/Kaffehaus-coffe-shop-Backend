package com.ucb.kaffehaus.inventario.receta.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.receta.domain.Receta;

public class RecetaResponse {
    private UUID id;
    private UUID productoId;
    private BigDecimal cantidad;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static RecetaResponse from(Receta receta) {
        RecetaResponse response = new RecetaResponse();
        response.id = receta.getId();
        response.productoId = receta.getProductoId();
        response.cantidad = receta.getCantidad();
        response.borrado = receta.isBorrado();
        return response;
    }
}
