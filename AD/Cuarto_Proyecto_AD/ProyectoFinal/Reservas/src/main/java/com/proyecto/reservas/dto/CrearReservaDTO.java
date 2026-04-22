package com.proyecto.reservas.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CrearReservaDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int habitacionId;
}