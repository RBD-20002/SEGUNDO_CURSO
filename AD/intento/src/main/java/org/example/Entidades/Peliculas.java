package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "peliculas")
@Setter
@Getter
@NoArgsConstructor
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private int id_pelicula;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "año_estreno", columnDefinition = "YEAR")
    private int ano_estreno;

    @Column(name = "genero", length = 50)
    private String genero;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "actuan",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actores> actores = new HashSet<>();

    @OneToOne(mappedBy = "peliculas")
    private Premios premios;

    @OneToMany(mappedBy = "peliculas")
    private List<Proyecciones> proyecciones = new ArrayList<>();

    public Peliculas(String titulo, int ano_estreno, String genero) {
        this.titulo = titulo;
        this.ano_estreno = ano_estreno;
        this.genero = genero;
    }
}