package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_libro;

    private String titulo;
    private double precio;

    @ManyToMany
    private List<Autores> autores = new ArrayList<>();


    public Libros(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
        this.autores = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Libros{" +
                "id_libro=" + id_libro +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", autores=" + autores +
                '}';
    }
}
