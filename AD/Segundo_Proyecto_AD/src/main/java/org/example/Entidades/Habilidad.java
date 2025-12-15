package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Habilidad")
@Getter
@Setter
@NoArgsConstructor
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idHabilidad;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToMany(mappedBy = "habilidad", fetch = FetchType.LAZY)
    private List<Personaje> personaje;

    @Override
    public String toString(){
        return "| HABILIDAD ID: "+idHabilidad+" | NOMBRE: "+nombre+" | DESCRIPCION: "+descripcion+" "+personaje;
    }
}
