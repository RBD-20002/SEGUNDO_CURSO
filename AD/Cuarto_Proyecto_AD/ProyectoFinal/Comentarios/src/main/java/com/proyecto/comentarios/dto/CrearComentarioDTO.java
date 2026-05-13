package com.proyecto.comentarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrearComentarioDTO {
    private String nombreHotel;
    private String nombreUsuario;
    private String contrasena;
    private Integer reservaId;
    private Double puntuacion;
    private String comentario;
}