package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Traje")
@Setter
@Getter
@NoArgsConstructor
public class Traje {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "especificacion", length = 100,nullable = false)
    private String especificacion;

    @OneToOne(mappedBy = "traje", fetch = FetchType.LAZY)
    private Personaje personaje;



    public Traje(int id, String especificacion) {
        this.id = id;
        this.especificacion = especificacion;
    }

    @Override
    public String toString(){
        return  "║        TRAJE        ║"+
                "\n║ TRAJE ID: "+ id +" ║"+
                "\n║ ESPECIFICACION: "+ especificacion +" ║";
    }
}
