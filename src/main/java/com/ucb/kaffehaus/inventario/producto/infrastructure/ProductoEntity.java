package com.ucb.kaffehaus.inventario.producto.infrastructure;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.categoria.infrastructure.CategoriaEntity;

@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;

    @Column(name = "unidad", nullable = false)
    private String unidad; // "gramos", "unidades", "mililitros"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    @Column(name = "vendible", nullable = false)
    private boolean vendible;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected ProductoEntity() {
    }

    public ProductoEntity(String nombre, Integer stock, BigDecimal precio, Integer stockMinimo, 
                          String unidad, CategoriaEntity categoria, boolean vendible) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.unidad = unidad;
        this.categoria = categoria;
        this.vendible = vendible;
        this.borrado = false;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public CategoriaEntity getCategoria() { return categoria; }
    public void setCategoria(CategoriaEntity categoria) { this.categoria = categoria; }

    public boolean isVendible() { return vendible; }
    public void setVendible(boolean vendible) { this.vendible = vendible; }

    public boolean isBorrado() { return borrado; }
    public void setBorrado(boolean borrado) { this.borrado = borrado; }
}