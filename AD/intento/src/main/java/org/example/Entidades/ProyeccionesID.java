package org.example.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public class ProyeccionesID {

    @Column(name = "pelicula_id")
    private int pelicula_id;

    @Column(name = "sala_id")
    private int sala_id;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(name = "horario", columnDefinition = "TIME")
    private LocalTime horario;

    public ProyeccionesID(int pelicula_id, int sala_id, LocalDate fecha, LocalTime horario) {
        this.pelicula_id = pelicula_id;
        this.sala_id = sala_id;
        this.fecha = fecha;
        this.horario = horario;
    }
}
