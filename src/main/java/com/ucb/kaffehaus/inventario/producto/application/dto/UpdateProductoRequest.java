package com.ucb.kaffehaus.inventario.producto.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

public class UpdateProductoRequest {
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

        if (stock != null && stock < 0) {
            result.addError("stock", "El stock no es valido");
        }
        if (precio != null && precio.compareTo(BigDecimal.ZERO) < 0) {
            result.addError("precio", "El precio no es valido");
        }
        if (stockMinimo != null && stockMinimo < 0) {
            result.addError("stockMinimo", "El stock minimo no es valido");
        }

        if (result.hasErrors()) throw new ValidationException(result);
        return result;
    }
}
