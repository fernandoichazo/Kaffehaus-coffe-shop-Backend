package com.ucb.kaffehaus.personal.entidad.infrastructure;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "entidad")
public class EntidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nit", nullable = false, unique = true)
    private String nit;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fec_c", nullable = false, updatable = false)
    private LocalDateTime fecC;

    @Column(name = "fec_u")
    private LocalDateTime fecU;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected EntidadEntity() {}

    public EntidadEntity(String nombre, String nit, String telefono, String descripcion) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.fecC = LocalDateTime.now();
        this.borrado = false;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNit() {return nit;}
    public void setNit(String nit) {this.nit = nit;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public LocalDateTime getFecC() {return fecC;}
    public void setFecC(LocalDateTime fecC) {this.fecC = fecC;}
    public LocalDateTime getFecU() {return fecU;}
    public void setFecU(LocalDateTime fecU) {this.fecU = fecU;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    @PreUpdate
    protected void onUpdate() {
        this.fecU = LocalDateTime.now();
    }
}
