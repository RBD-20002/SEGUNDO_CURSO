package org.example.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telefono {

    @Id
    private String dni;

    private String numTelf;

    @OneToOne
    @MapsId
    @JoinColumn(name = "dni")
    private Autores autor;

    @Override
    public String toString() {
        return "Telefono{" +
                "dni='" + dni + '\'' +
                ", numTelf='" + numTelf + '\'' +
                ", autor=" + autor +
                '}';
    }
}
