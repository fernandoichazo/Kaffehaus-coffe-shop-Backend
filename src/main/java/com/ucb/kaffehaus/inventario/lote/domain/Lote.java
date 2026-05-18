package com.ucb.kaffehaus.inventario.lote.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Lote extends AggregateRoot<UUID> {

    private LocalDate fechaCompra;
    private UUID proveedorId;
    private BigDecimal total;
    private boolean borrado;

    private Lote(UUID id, LocalDate fechaCompra, UUID proveedorId, BigDecimal total, boolean borrado) {
        super(id);
        this.fechaCompra = fechaCompra;
        this.proveedorId = proveedorId;
        this.total = total;
        this.borrado = borrado;
    }

    public static Lote create(LocalDate fechaCompra, UUID proveedorId, BigDecimal total) {
        return new Lote(null, fechaCompra, proveedorId, total, false);
    }

    public static Lote restore(UUID id, LocalDate fechaCompra, UUID proveedorId, BigDecimal total, boolean borrado) {
        return new Lote(id, fechaCompra, proveedorId, total, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

    public LocalDate getFechaCompra() {return fechaCompra;}
    public void setFechaCompra(LocalDate fechaCompra) {this.fechaCompra = fechaCompra;}
    public UUID getProveedorId() {return proveedorId;}
    public void setProveedorId(UUID proveedorId) {this.proveedorId = proveedorId;}
    public BigDecimal getTotal() {return total;}
    public void setTotal(BigDecimal total) {this.total = total;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
