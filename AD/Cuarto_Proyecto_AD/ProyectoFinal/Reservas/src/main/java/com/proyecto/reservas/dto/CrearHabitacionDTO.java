package com.proyecto.reservas.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CrearHabitacionDTO {
    private int numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private int idHotel;
}
