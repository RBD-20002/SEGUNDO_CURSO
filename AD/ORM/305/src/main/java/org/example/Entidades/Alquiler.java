package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "alquiler")
@Getter
@Setter
@NoArgsConstructor
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlquiler;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(name = "alquilado")
    private boolean alquilado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLibro")
    private Libro libro;


    public Alquiler(Libro libro, Cliente cliente, LocalDate fecha, boolean alquilado) {
        this.libro = libro;
        this.cliente = cliente;
        this.fecha = fecha;
        this.alquilado = alquilado;
    }

    @Override
    public String toString() {
        return "Alquiler:" +
                "\nLibro= " + libro +
                "\nCliente= " + cliente +
                "\nAlquilado= " + alquilado +
                "\nFecha= " + fecha;
    }
}
