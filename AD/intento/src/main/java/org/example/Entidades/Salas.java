package org.example.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salas")
@Setter
@Getter
@NoArgsConstructor
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int id_sala;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "capacidad")
    private int capacidad;

    @OneToMany(mappedBy = "salas")
    private List<Proyecciones> proyecciones = new ArrayList<>();
}