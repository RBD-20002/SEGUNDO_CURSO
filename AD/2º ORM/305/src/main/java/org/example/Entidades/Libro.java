package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private int idLibro;

    @Column(name = "codigo", columnDefinition = "VARCHAR(20)")
    private String codigo;

    @Column(name = "titulo", columnDefinition = "VARCHAR(200)")
    private String titulo;

    @Column(name = "autores", columnDefinition = "VARCHAR(300)")
    private String autores;

    @Column(name = "año", columnDefinition = "INT(11)")
    private int ano;

    @OneToMany(mappedBy = "libro")
    private List<Alquiler> alquileres = new ArrayList<>();


    public Libro(String codigo, String titulo, String autores, int ano){
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Libro:" +
                "\nIdLibro=" + idLibro +
                "\nCodigo='" + codigo +
                "\nTitulo='" + titulo +
                "\nAutores='" + autores +
                "\nAño=" + ano;
    }
}
