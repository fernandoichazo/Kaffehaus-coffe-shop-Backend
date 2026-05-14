package com.ucb.kaffehaus.personal.proveedor.infrastructure;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.ucb.kaffehaus.personal.entidad.infrastructure.EntidadEntity;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaEntity;

@Entity
@Table(name = "proveedor")
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad", nullable = true)
    private EntidadEntity entidad;

    @Column(name = "fec_c", nullable = false, updatable = false)
    private LocalDateTime fecC;

    @Column(name = "fec_u")
    private LocalDateTime fecU;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    protected ProveedorEntity() {}

    public ProveedorEntity(PersonaEntity persona, EntidadEntity entidad) {
        this.persona = persona;
        this.entidad = entidad;
        this.fecC = LocalDateTime.now();
        this.borrado = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fecU = LocalDateTime.now();
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public PersonaEntity getPersona() {return persona;}
    public void setPersona(PersonaEntity persona) {this.persona = persona;}
    public EntidadEntity getEntidad() {return entidad;}
    public void setEntidad(EntidadEntity entidad) {this.entidad = entidad;}
    public LocalDateTime getFecC() {return fecC;}
    public void setFecC(LocalDateTime fecC) {this.fecC = fecC;}
    public LocalDateTime getFecU() {return fecU;}
    public void setFecU(LocalDateTime fecU) {this.fecU = fecU;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}
}
