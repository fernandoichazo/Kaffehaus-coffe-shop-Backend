package com.ucb.kaffehaus.inventario.receta.infrastructure;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoEntity;

@Entity
@Table(name = "receta")
public class RecetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false, unique = true)
    private ProductoEntity producto;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    protected RecetaEntity() {
    }

    public RecetaEntity(ProductoEntity producto, BigDecimal cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
