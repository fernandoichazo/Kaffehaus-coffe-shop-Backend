package com.ucb.kaffehaus.inventario.producto.domain;

import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.shared.domain.AggregateRoot;

public class Producto extends AggregateRoot<UUID> {

    private String nombre;
    private Integer stock;
    private BigDecimal precio;
    private Integer stockMinimo;
    private String unidad;
    private Categoria categoria;
    private boolean vendible;
    private boolean borrado;

    private Producto(UUID id, String nombre, Integer stock, BigDecimal precio, Integer stockMinimo,
            String unidad, Categoria categoria, boolean vendible, boolean borrado) {
        super(id);
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.unidad = unidad;
        this.categoria = categoria;
        this.vendible = vendible;
        this.borrado = borrado;
    }

    public static Producto create(String nombre, Integer stock, BigDecimal precio, Integer stockMinimo,
            String unidad, Categoria categoria, boolean vendible) {
        return new Producto(null, nombre, stock, precio, stockMinimo, unidad, categoria, vendible, false);
    }

    public static Producto restore(UUID id, String nombre, Integer stock, BigDecimal precio, Integer stockMinimo,
            String unidad, Categoria categoria, boolean vendible, boolean borrado) {
        return new Producto(id, nombre, stock, precio, stockMinimo, unidad, categoria, vendible, borrado);
    }

    public void delete() {
        this.borrado = true;
    }

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
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public boolean isVendible() {return vendible;}
    public void setVendible(boolean vendible) {this.vendible = vendible;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
