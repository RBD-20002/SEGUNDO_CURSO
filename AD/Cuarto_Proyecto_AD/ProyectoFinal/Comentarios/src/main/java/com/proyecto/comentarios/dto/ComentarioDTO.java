package com.proyecto.comentarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {
    private String nombreHotel;
    private Integer reserva_id;
    private Double puntuacion;
    private String comentario;
}