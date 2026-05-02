package com.proyecto.reservas.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ActualizarHabitacionDTO {
    private int id;
    private int numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private int idHotel;
    private Boolean disponible;
}