package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proyecciones")
@Setter
@Getter
@NoArgsConstructor
public class Proyecciones {

    @EmbeddedId
    private ProyeccionesID proyeccionesID;


    @ManyToOne
    @MapsId("sala_id")
    @JoinColumn(name = "sala_id")
    private Salas salas;

    @ManyToOne
    @MapsId("pelicula_id")
    @JoinColumn(name = "pelicula_id")
    private Peliculas peliculas;
}