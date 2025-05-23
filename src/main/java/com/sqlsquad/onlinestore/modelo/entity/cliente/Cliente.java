// Grupo 2 - SQL SQUAD

package com.sqlsquad.onlinestore.modelo.entity.cliente;

import jakarta.persistence.*;
import com.sqlsquad.onlinestore.modelo.enums.TipoCliente;

@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Nos permite implementar toda la herencia en una sola tabla
@DiscriminatorColumn(name = "tipoCliente", discriminatorType = DiscriminatorType.STRING) // Discriminamos por tipo de cliente
public abstract class Cliente {


    @Enumerated(EnumType.STRING)
    @Column(name = "tipoCliente", nullable = false, insertable = false, updatable = false)
    private TipoCliente tipoCliente;

    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Column(name = "domicilio", nullable = false, length = 100)
    private String domicilio;

    @Column(name = "nif", nullable = false, length = 20)
    private String nif;

    @Id
    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

    // Constructor para Hibernate
    public Cliente() {}

    public Cliente(String nombre, String domicilio, String nif, String email, TipoCliente tipoCliente) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public String getNif() {
        return nif;
    }
    public String getEmail() {
        return email;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Domicilio: " + domicilio + " Nif: " + nif + " Email: " + email;
    }
}
