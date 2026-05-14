package com.ucb.kaffehaus.inventario.categoria.infrastructure;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected CategoriaEntity() {}

    public CategoriaEntity(String nombre) {
        this.nombre = nombre;
        this.borrado = false;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isBorrado() { return borrado; }
    public void setBorrado(boolean borrado) { this.borrado = borrado; }
}