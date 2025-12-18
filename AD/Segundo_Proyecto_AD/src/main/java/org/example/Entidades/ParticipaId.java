package org.example.Entidades;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
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
        return "| PERSONAJE ID: "+idPersonaje+" | EVENTO ID: "+idEvento;
    }
}
