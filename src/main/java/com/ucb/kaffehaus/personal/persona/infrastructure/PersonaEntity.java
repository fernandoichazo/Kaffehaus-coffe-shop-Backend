package com.ucb.kaffehaus.personal.persona.infrastructure;
import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "borrado", nullable = false)
    private boolean borrado;

    public PersonaEntity(Integer id, String nombre, String apellidos, String telefono, String dni, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.borrado = borrado;
    }
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}
    public boolean isBorrado() {return borrado;}
    public void setBorrado(boolean borrado) {this.borrado = borrado;}

    
}
