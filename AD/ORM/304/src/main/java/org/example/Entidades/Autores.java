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
public class Autores {

    @Id
    private String dni;

    private String nombre;
    private String nacionalidad;

    @OneToOne(mappedBy = "autor")
    private Telefono telefono;

    @ManyToMany
    @JoinTable(name = "Libros_Autores",
        joinColumns = @JoinColumn(name = "id_autor"),
        inverseJoinColumns = @JoinColumn(name = "id_libro")
    )
    private List<Libros> libros = new ArrayList<>();


    public Autores(String dni, String nombre, String nacionalidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.libros = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Autores{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", telefono=" + telefono +
                ", libros=" + libros +
                '}';
    }
}
