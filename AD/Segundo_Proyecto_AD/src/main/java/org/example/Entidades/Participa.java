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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Personaje personaje;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "rol", length = 20)
    private String rol;



    public Participa(Evento evento, Personaje personaje, Date fecha, String rol) {
        this.evento = evento;
        this.personaje = personaje;
        this.fecha = fecha;
        this.rol = rol;
    }

    @Override
    public String toString(){
        return "| PARTICIPA ID: "+id+" "+evento+personaje+" | FECHA: "+fecha+" | ROL: "+rol+" |";
    }
}
