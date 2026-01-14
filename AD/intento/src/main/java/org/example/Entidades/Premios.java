package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "premios")
@Setter
@Getter
@NoArgsConstructor
public class Premios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premio")
    private int id_premio;

    @OneToOne
    @JoinColumn(name = "pelicula_id", unique = true)
    private Peliculas peliculas;

    @Column(name = "nombre_premio", length = 100)
    private String nombre_premio;

    @Column(name = "año_premio", columnDefinition = "YEAR")
    private int ano_premio;

    public Premios(String nombre_premio, int ano_premio) {
        this.nombre_premio = nombre_premio;
        this.ano_premio = ano_premio;
    }
}