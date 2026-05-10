package com.proyecto.comentarios.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Document(collection = "comentarios")
@Getter
@Setter
public class Comentarios {

    @Id
    private String id;

    @Field(name = "usuario_id")
    private Integer usuarioId;

    @Field(name = "hotel_id")
    private Integer hotelId;

    @Field(name = "reserva_id")
    private Integer reservaId;

    private Double puntuacion;
    private String comentario;

    private LocalDateTime fechaCreacion;
}