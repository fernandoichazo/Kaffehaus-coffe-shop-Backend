package com.ucb.kaffehaus.inventario.detalleLote.infrastructure;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.inventario.lote.infrastructure.LoteEntity;
import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoEntity;

@Entity
@Table(name = "detalle_lote")
public class DetalleLoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lote", nullable = false)
    private LoteEntity lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "fecha_acabado")
    private LocalDate fechaAcabado;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected DetalleLoteEntity() {
    }

    public DetalleLoteEntity(LoteEntity lote, ProductoEntity producto, BigDecimal cantidad, 
                             BigDecimal precioUnitario, LocalDate fechaAcabado) {
        this.lote = lote;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaAcabado = fechaAcabado;
        this.subtotal = cantidad.multiply(precioUnitario);
        this.borrado = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
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

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDate getFechaAcabado() {
        return fechaAcabado;
    }

    public void setFechaAcabado(LocalDate fechaAcabado) {
        this.fechaAcabado = fechaAcabado;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
