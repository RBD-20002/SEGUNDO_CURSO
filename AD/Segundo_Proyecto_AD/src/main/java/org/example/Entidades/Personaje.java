package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Personaje")
@Getter
@Setter
@NoArgsConstructor
public class Personaje {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "alias", length = 100)
    private String alias;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_traje")
    private Traje traje;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Personaje_Habilidad",
            joinColumns = @JoinColumn(name = "id_personaje"),
            inverseJoinColumns = @JoinColumn(name = "id_habilidad")
    )
    private List<Habilidad> habilidad = new ArrayList<>();



    public Personaje(int id, String nombre, String alias, Traje traje) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
        this.traje = traje;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("║      PERSONAJE      ║\n")
                .append("║ ID: "+id+" ║\n")
                .append("║ NOMBRE: "+nombre+" ║\n")
                .append("║ ALIAS: "+alias+" ║\n");
        if(traje != null){
            sb.append(traje.toString());
        }
        sb.append("║      HABILIDADES      ║\n");
                if(!habilidad.isEmpty()){
                    for(Habilidad hab : habilidad){
                        sb.append("║ "+hab.getNombre()+" ║");
                    }
                }else{
                    sb.append("║      SIN HABILIDADES      ║\n");
                }
                return sb.toString();
    }
}
