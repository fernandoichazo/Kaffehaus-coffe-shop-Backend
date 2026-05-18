package com.ucb.kaffehaus.inventario.producto.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class CreateProductoRequest {
    private String nombre;
    private Integer stock;
    private BigDecimal precio;
    private Integer stockMinimo;
    private String unidad;
    private UUID categoriaId;
    private boolean vendible;

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}
    public BigDecimal getPrecio() {return precio;}
    public void setPrecio(BigDecimal precio) {this.precio = precio;}
    public Integer getStockMinimo() {return stockMinimo;}
    public void setStockMinimo(Integer stockMinimo) {this.stockMinimo = stockMinimo;}
    public String getUnidad() {return unidad;}
    public void setUnidad(String unidad) {this.unidad = unidad;}
    public UUID getCategoriaId() {return categoriaId;}
    public void setCategoriaId(UUID categoriaId) {this.categoriaId = categoriaId;}
    public boolean isVendible() {return vendible;}
    public void setVendible(boolean vendible) {this.vendible = vendible;}

    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        if (nombre == null || nombre.trim().isEmpty()) {
            result.addError("nombre", "El nombre es obligatorio");
        }
        if (stock == null || stock < 0) {
            result.addError("stock", "El stock es obligatorio");
        }
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
            result.addError("precio", "El precio es obligatorio");
        }
        if (stockMinimo == null || stockMinimo < 0) {
            result.addError("stockMinimo", "El stock minimo es obligatorio");
        }
        if (unidad == null || unidad.trim().isEmpty()) {
            result.addError("unidad", "La unidad es obligatoria");
        }
        if (categoriaId == null) {
            result.addError("categoriaId", "La categoria es obligatoria");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
