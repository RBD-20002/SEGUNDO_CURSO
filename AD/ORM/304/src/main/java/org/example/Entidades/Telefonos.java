package org.example.Entidades;

import jakarta.persistence.*;

@Entity
public class Telefonos {

    @Id
    @OneToOne
    private Autores autor;

    private String numTelf;

    public Telefonos() {
    }

    public Telefonos(Autores dni, String numTelf) {
        super();
        this.autor = dni;
        this.numTelf = numTelf;
    }

    public Autores getAutor() {
        return autor;
    }
    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public String getNumTelf() {
        return numTelf;
    }
    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }
}
