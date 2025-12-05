package org.example.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_libro;

    private String titulo;
    private double precio;

    @ManyToMany(mappedBy = "libros")
    private List<Autores> autores = new ArrayList<>();

    public Libros() {
    }

    public Libros(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public int getId_libro() {
        return id_libro;
    }
    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Autores> getAutores() {
        return autores;
    }
    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }

    @Override
    public String toString(){
        return "LIBRO ID: "+id_libro+"\nTITULO: "+titulo+"\nPRECIO: "+precio;
    }
}
