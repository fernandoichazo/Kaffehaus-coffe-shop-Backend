package com.ucb.kaffehaus.inventario.lote.infrastructure;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.ucb.kaffehaus.personal.proveedor.infrastructure.ProveedorEntity;

@Entity
@Table(name = "lote")
public class LoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    @Column(name = "total", nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected LoteEntity() {
    }

    public LoteEntity(LocalDate fechaCompra, ProveedorEntity proveedor, BigDecimal total) {
        this.fechaCompra = fechaCompra;
        this.proveedor = proveedor;
        this.total = total;
        this.borrado = false;
    }

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    public LocalDate getFechaCompra() {return fechaCompra;}
    public void setFechaCompra(LocalDate fechaCompra) {this.fechaCompra = fechaCompra;}
    public ProveedorEntity getProveedor() {return proveedor;}
    public void setProveedor(ProveedorEntity proveedor) {this.proveedor = proveedor;}
    public BigDecimal getTotal() {return total;}
    public void setTotal(BigDecimal total) {this.total = total;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
