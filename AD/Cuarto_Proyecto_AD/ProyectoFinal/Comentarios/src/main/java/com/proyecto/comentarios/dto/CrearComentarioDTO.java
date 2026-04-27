package com.proyecto.comentarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearComentarioDTO {
    private String nombreHotel;
    private Integer idReserva;
    private Double puntuacion;
    private String comentario;
    private String usuario;
    private String contrasena;
}