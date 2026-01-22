package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private int idCliente;

    @Column(name = "DNI", columnDefinition = "CHAR(9)")
    private String DNI;

    @Column(name = "nombre", columnDefinition = "VARCHAR(200)")
    private String nombre;

    @Column(name = "email", columnDefinition = "VARCHAR(200)")
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Alquiler> alquileres = new ArrayList<>();


    public Cliente(String DNI, String nombre, String email) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente:" +
                "\nidCliente=" + idCliente +
                "\nDNI=" + DNI +
                "\nNombre='" + nombre +
                "\nEmail='" + email;
    }
}
