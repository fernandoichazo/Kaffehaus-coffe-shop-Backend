package com.ucb.kaffehaus.inventario.receta.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateRecetaRequest {
    private UUID productoId;
    private BigDecimal cantidad;

    public UUID getProductoId() {return productoId;}
    public void setProductoId(UUID productoId) {this.productoId = productoId;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (productoId == null) {
            result.addError("productoId", "El producto es obligatorio");
        }
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            result.addError("cantidad", "La cantidad es obligatoria");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
