package com.ucb.kaffehaus.inventario.lote.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.lote.domain.Lote;

public class LoteResponse {
    private UUID id;
    private LocalDate fechaCompra;
    private UUID proveedorId;
    private BigDecimal total;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public LocalDate getFechaCompra() {return fechaCompra;}
    public void setFechaCompra(LocalDate fechaCompra) {this.fechaCompra = fechaCompra;}
    public UUID getProveedorId() {return proveedorId;}
    public void setProveedorId(UUID proveedorId) {this.proveedorId = proveedorId;}
    public BigDecimal getTotal() {return total;}
    public void setTotal(BigDecimal total) {this.total = total;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static LoteResponse from(Lote lote) {
        LoteResponse response = new LoteResponse();
        response.id = lote.getId();
        response.fechaCompra = lote.getFechaCompra();
        response.proveedorId = lote.getProveedorId();
        response.total = lote.getTotal();
        response.borrado = lote.isBorrado();
        return response;
    }
}
