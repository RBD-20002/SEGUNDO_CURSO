package com.proyecto.comentarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComentarioDTO {
    private String nombreHotel;
    private Integer reserva_id;
    private Double puntuacion;
    private String comentario;
}