package com.ucb.kaffehaus.inventario.detalleLote.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class DetalleLote extends AggregateRoot<UUID> {

    private UUID loteId;
    private UUID productoId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private LocalDate fechaAcabado;
    private boolean borrado;

    private DetalleLote(UUID id, UUID loteId, UUID productoId, BigDecimal cantidad,
            BigDecimal precioUnitario, BigDecimal subtotal, LocalDate fechaAcabado, boolean borrado) {
        super(id);
        this.loteId = loteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.fechaAcabado = fechaAcabado;
        this.borrado = borrado;
    }

    public static DetalleLote create(UUID loteId, UUID productoId, BigDecimal cantidad,
            BigDecimal precioUnitario, LocalDate fechaAcabado) {
        BigDecimal subtotal = cantidad.multiply(precioUnitario);
        return new DetalleLote(null, loteId, productoId, cantidad, precioUnitario, subtotal, fechaAcabado, false);
    }

    public static DetalleLote restore(UUID id, UUID loteId, UUID productoId, BigDecimal cantidad,
            BigDecimal precioUnitario, BigDecimal subtotal, LocalDate fechaAcabado, boolean borrado) {
        return new DetalleLote(id, loteId, productoId, cantidad, precioUnitario, subtotal, fechaAcabado, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

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
}
