package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Evento")
@Getter
@Setter
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "lugar", length = 100)
    private String lugar;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Participa> participa = new ArrayList<>();



    public Evento(String nombre, String lugar) {
        this.nombre = nombre;
        this.lugar = lugar;
    }

    @Override
    public String toString(){
        return "| EVENTO ID: "+id+" | NOMBRE: "+nombre+" | LUGAR: "+lugar+" |";
    }
}
