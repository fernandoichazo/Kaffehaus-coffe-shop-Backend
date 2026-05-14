package com.ucb.kaffehaus.personal.rol.infrastructure;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rol")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "fec_c", nullable = false, updatable = false)
    private LocalDateTime fecC;

    @Column(name = "fec_u")
    private LocalDateTime fecU;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected RolEntity() {
    }

    public RolEntity(String nombre) {
        this.nombre = nombre;
        this.fecC = LocalDateTime.now();
        this.borrado = false;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
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
