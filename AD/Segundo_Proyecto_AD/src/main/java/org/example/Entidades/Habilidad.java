package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Habilidad")
@Getter
@Setter
@NoArgsConstructor
public class Habilidad {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre",length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToMany(mappedBy = "habilidad")
    public List<Personaje> personajes = new ArrayList<>();



    public Habilidad(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return "| HABILIDAD ID: "+id+" | NOMBRE: "+nombre+" | DESCRIPCION: "+descripcion+" |";
    }
}
