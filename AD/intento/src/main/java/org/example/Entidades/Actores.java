package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actores")
@Setter
@Getter
@NoArgsConstructor
public class Actores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actor")
    private int id_actor;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "fecha_nacimiento", columnDefinition = "DATE")
    private LocalDate fecha_nacimiento;

    @Column(name = "nacionalidad", length = 100)
    private String nacionalidad;

    @ManyToMany(mappedBy = "actores", fetch = FetchType.LAZY)
    private Set<Peliculas> peliculas = new HashSet<>();

    public Actores(String nombre, LocalDate fecha_nacimiento, String nacionalidad) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
    }
}