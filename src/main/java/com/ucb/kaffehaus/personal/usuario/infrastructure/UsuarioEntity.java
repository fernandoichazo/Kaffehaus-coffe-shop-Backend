package com.ucb.kaffehaus.personal.usuario.infrastructure;
import java.time.LocalDateTime;

import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    private PersonaEntity persona;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "fec_c", nullable = false, updatable = false)
    private LocalDateTime fecC;

    @Column(name = "fec_u")
    private LocalDateTime fecU;

    protected UsuarioEntity() {}

    public UsuarioEntity(PersonaEntity persona, String correo, String contrasena) {
        this.persona = persona;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fecC = LocalDateTime.now(); 
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public PersonaEntity getPersona() {return persona;}
    public void setPersona(PersonaEntity persona) {this.persona = persona;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
    public LocalDateTime getFecC() {return fecC;}
    public void setFecC(LocalDateTime fecC) {this.fecC = fecC;}
    public LocalDateTime getFecU() {return fecU;}
    public void setFecU(LocalDateTime fecU) {this.fecU = fecU;}

    @PreUpdate
    protected void onUpdate() {
        this.fecU = LocalDateTime.now();
    }
}
