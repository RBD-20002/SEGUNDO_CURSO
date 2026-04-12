package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "nombre", columnDefinition = "DEFAULT NULL", length = 100)
    private String nombre;

    @Column(name = "direccion", columnDefinition = "DEFAULT NULL")
    private String direccion;
}
