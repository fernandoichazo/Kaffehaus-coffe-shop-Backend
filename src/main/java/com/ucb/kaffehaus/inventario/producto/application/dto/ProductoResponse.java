package com.ucb.kaffehaus.inventario.producto.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.producto.domain.Producto;

public class ProductoResponse {
    private UUID id;
    private String nombre;
    private Integer stock;
    private BigDecimal precio;
    private Integer stockMinimo;
    private String unidad;
    private UUID categoriaId;
    private boolean vendible;
    private boolean borrado;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
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
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    public static ProductoResponse from(Producto producto) {
        ProductoResponse response = new ProductoResponse();
        response.id = producto.getId();
        response.nombre = producto.getNombre();
        response.stock = producto.getStock();
        response.precio = producto.getPrecio();
        response.stockMinimo = producto.getStockMinimo();
        response.unidad = producto.getUnidad();
        response.categoriaId = producto.getCategoria() != null ? producto.getCategoria().getId() : null;
        response.vendible = producto.isVendible();
        response.borrado = producto.isBorrado();
        return response;
    }
}
