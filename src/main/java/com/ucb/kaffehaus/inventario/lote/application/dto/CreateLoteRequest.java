package com.ucb.kaffehaus.inventario.lote.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateLoteRequest {
    private LocalDate fechaCompra;
    private UUID proveedorId;
    private BigDecimal total;

    public LocalDate getFechaCompra() {return fechaCompra;}
    public void setFechaCompra(LocalDate fechaCompra) {this.fechaCompra = fechaCompra;}
    public UUID getProveedorId() {return proveedorId;}
    public void setProveedorId(UUID proveedorId) {this.proveedorId = proveedorId;}
    public BigDecimal getTotal() {return total;}
    public void setTotal(BigDecimal total) {this.total = total;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (fechaCompra == null) {
            result.addError("fechaCompra", "La fecha de compra es obligatoria");
        }
        if (proveedorId == null) {
            result.addError("proveedorId", "El proveedor es obligatorio");
        }
        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
            result.addError("total", "El total es obligatorio");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
