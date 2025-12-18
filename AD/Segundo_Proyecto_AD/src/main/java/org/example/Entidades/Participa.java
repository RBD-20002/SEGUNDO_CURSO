package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Participa")
@Getter
@Setter
@NoArgsConstructor
public class Participa {

    @EmbeddedId
    private ParticipaId idParticipa;

    @Column(name = "fecha", columnDefinition = "DATE")
    private Date fecha;

    @Column(name = "rol", length = 50)
    private String rol;

    @MapsId("idEvento")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento",nullable = false)
    private Evento evento;

    @MapsId("idPersonaje")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personaje",nullable = false)
    private Personaje personaje;



    public Participa(int idPersonaje, int idEvento, Date fecha, String rol) {
        this.idParticipa = new ParticipaId(idPersonaje,idEvento);
        this.fecha = fecha;
        this.rol = rol;
    }

    @Override
    public String toString(){
        return idParticipa.toString()+" | FECHA: "+fecha+" | ROL: "+rol+" |";
    }
}