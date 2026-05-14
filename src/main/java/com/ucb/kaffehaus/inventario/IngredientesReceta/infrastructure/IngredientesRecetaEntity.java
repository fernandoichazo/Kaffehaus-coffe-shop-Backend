package com.ucb.kaffehaus.inventario.IngredientesReceta.infrastructure;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoEntity;
import com.ucb.kaffehaus.inventario.receta.infrastructure.RecetaEntity;

@Entity
@Table(name = "ingredientes_receta")
public class IngredientesRecetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta", nullable = false)
    private RecetaEntity receta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    protected IngredientesRecetaEntity() {
    }

    public IngredientesRecetaEntity(RecetaEntity receta, ProductoEntity producto, BigDecimal cantidad) {
        this.receta = receta;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public RecetaEntity getReceta() {return receta;}
    public void setReceta(RecetaEntity receta) {this.receta = receta;}
    public ProductoEntity getProducto() {return producto;}
    public void setProducto(ProductoEntity producto) {this.producto = producto;}
    public BigDecimal getCantidad() {return cantidad;}
    public void setCantidad(BigDecimal cantidad) {this.cantidad = cantidad;}
}
