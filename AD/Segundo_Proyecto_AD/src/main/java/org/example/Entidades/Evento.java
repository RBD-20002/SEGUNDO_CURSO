package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Evento")
@Getter
@Setter
@NoArgsConstructor
public class Evento {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "lugar", length = 100)
    private String lugar;

    @OneToMany(mappedBy = "evento")
    private List<Participa> participantes;



    public Evento(int id, String nombre, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    @Override
    public String toString(){
        return  "║      EVENTO      ║"+
                "\n║ EVENTO ID: "+ id +"  ║" +
                "\n║ NOMBRE: "+ nombre +" ║"+
                "\n║ LUGAR: "+ (lugar != null ? lugar : "N/A") +" ║\n";
    }
}
