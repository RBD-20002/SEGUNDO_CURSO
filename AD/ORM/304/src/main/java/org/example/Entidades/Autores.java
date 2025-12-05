package org.example.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autores {

    @Id
    private String DNI_autor;

    private String nombre;
    private String nacionalidad;

    @ManyToMany
    @JoinTable(name = "Libros_Autores",
        joinColumns = @JoinColumn(name = "DNI_Autor"),
        inverseJoinColumns = @JoinColumn(name = "id_libro"))
    public List<Libros> libros = new ArrayList<>();

    @OneToOne(mappedBy = "autor")
    @JoinColumn(name = "dni_autor")
    private Telefonos telefono;

    public Autores() {
    }

    public Autores(String DNI_autor, String nombre, String nacionalidad) {
        this.DNI_autor = DNI_autor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getDNI_autor() {
        return DNI_autor;
    }
    public void setDNI_autor(String DNI_autor) {
        this.DNI_autor = DNI_autor;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Libros> getLibros() {
        return libros;
    }
    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    public void addLibro(Libros libro){
        libros.add(libro);

    }

    @Override
    public String toString(){
        return "DNI: "+DNI_autor+"\nNOMBRE: "+nombre+"\nNACIONALIDAD: "+nacionalidad;
    }
}
