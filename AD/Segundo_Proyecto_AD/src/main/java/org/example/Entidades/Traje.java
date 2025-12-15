package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Traje")
@Getter
@Setter
@NoArgsConstructor
public class Traje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "especificacion", length = 100, nullable = false)
    private String especificacion;

    @OneToOne(mappedBy = "traje", fetch = FetchType.LAZY)
    private Personaje personaje;



    public Traje(int id, String especificacion) {
        this.id = id;
        this.especificacion = especificacion;
    }

    @Override
    public String toString(){
        return "| TRAJE ID: "+id+" | ESPECIFICACION: "+especificacion+" |";
    }
}
