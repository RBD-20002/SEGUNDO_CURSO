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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "alias", length = 100)
    private String alias;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id",unique = true)
    private Traje traje;

    @ManyToMany
    @JoinTable(name = "Personaje_Habilidad",
            joinColumns = @JoinColumn(name = "id_Personaje"),
            inverseJoinColumns = @JoinColumn(name = "id_Habilidad")
    )
    private List<Habilidad> habilidad;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<Participa> participa = new ArrayList<>();



    public Personaje(String nombre, String alias, Traje traje) {
        this.nombre = nombre;
        this.alias = alias;
        this.traje = traje;
        this.habilidad = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "| PERSONAJE ID: "+id+" | NOMBRE: "+nombre+" | ALIAS: "+alias+" | "+traje+" | "+habilidad;
    }
}
