package com.ucb.kaffehaus.inventario.detalleLote.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateDetalleLoteRequest {
    private UUID loteId;
    private UUID productoId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private LocalDate fechaAcabado;

    public UUID getLoteId() {return loteId;}
    public void setLoteId(UUID loteId) {this.loteId = loteId;}
    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
    public BigDecimal getPrecioUnitario() {return precioUnitario;}
    public void setPrecioUnitario(BigDecimal precioUnitario) {this.precioUnitario = precioUnitario;}
    public LocalDate getFechaAcabado() {return fechaAcabado;}
    public void setFechaAcabado(LocalDate fechaAcabado) {this.fechaAcabado = fechaAcabado;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (loteId == null) {
            result.addError("loteId", "El lote es obligatorio");
        }
        if (productoId == null) {
            result.addError("productoId", "El producto es obligatorio");
        }
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            result.addError("cantidad", "La cantidad es obligatoria");
        }
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) < 0) {
            result.addError("precioUnitario", "El precio unitario es obligatorio");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
