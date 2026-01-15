package org.example.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class ParticipaId implements Serializable {

    @Column(name = "id_personaje")
    private int idPersonaje;

    @Column(name = "id_evento")
    private int idEvento;



    public ParticipaId(int idPersonaje, int idEvento) {
        this.idPersonaje = idPersonaje;
        this.idEvento = idEvento;
    }

    @Override
    public String toString(){
        return  "║        PARTICIPA        ║"+
                "\n║ PERSONAJE ID: "+ idPersonaje +" ║"+
                "\n║ EVENTO ID: "+ idEvento +" ║\n";
    }
}
