package com.proyecto.comentarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {
    private String nombreHotel;
    private Integer reservaId;
    private Double puntuacion;
    private String comentario;
}