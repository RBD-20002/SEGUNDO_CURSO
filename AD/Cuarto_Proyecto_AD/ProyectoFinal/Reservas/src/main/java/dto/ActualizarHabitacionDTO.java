package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarHabitacionDTO {
    private int id;
    private int numeroHabitacion;
    private String tipo;
    private double precio;
    private int idHotel;
    private Boolean disponible;
}