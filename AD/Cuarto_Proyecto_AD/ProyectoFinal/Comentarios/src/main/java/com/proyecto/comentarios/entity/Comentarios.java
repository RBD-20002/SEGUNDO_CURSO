package com.proyecto.comentarios.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comentarios")
@Getter
@Setter
@NoArgsConstructor
public class Comentarios {

    @Id
    private String id;

    private int usuarioId;
    private int hotelId;
    private int reservaId;
    private double puntuacion;
    private String comentario;
    private String fechaCreacion;

    public Comentarios(Integer usuarioId, Integer hotelId, Integer reservaId, Double puntuacion, String comentario, LocalDateTime now) {
        this.usuarioId = usuarioId;
        this.hotelId = hotelId;
        this.reservaId = reservaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaCreacion = now.toString();
    }
}