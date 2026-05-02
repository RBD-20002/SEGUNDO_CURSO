package com.proyecto.reservas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarHotelDTO {
    private Integer hotelId;
    private String nombre;
    private String direccion;
}