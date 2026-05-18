package com.ucb.kaffehaus.inventario.detalleLote.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;

public class DetalleLoteResponse {
    private UUID id;
    private UUID loteId;
    private UUID productoId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private LocalDate fechaAcabado;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public UUID getLoteId() {return loteId;}
    public void setLoteId(UUID loteId) {this.loteId = loteId;}
    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
    public BigDecimal getPrecioUnitario() {return precioUnitario;}
    public void setPrecioUnitario(BigDecimal precioUnitario) {this.precioUnitario = precioUnitario;}
    public BigDecimal getSubtotal() {return subtotal;}
    public void setSubtotal(BigDecimal subtotal) {this.subtotal = subtotal;}
    public LocalDate getFechaAcabado() {return fechaAcabado;}
    public void setFechaAcabado(LocalDate fechaAcabado) {this.fechaAcabado = fechaAcabado;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static DetalleLoteResponse from(DetalleLote detalleLote) {
        DetalleLoteResponse response = new DetalleLoteResponse();
        response.id = detalleLote.getId();
        response.loteId = detalleLote.getLoteId();
        response.productoId = detalleLote.getProductoId();
        response.cantidad = detalleLote.getCantidad();
        response.precioUnitario = detalleLote.getPrecioUnitario();
        response.subtotal = detalleLote.getSubtotal();
        response.fechaAcabado = detalleLote.getFechaAcabado();
        response.borrado = detalleLote.isBorrado();
        return response;
    }
}
