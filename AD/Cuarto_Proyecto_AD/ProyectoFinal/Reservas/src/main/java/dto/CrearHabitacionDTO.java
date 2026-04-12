package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrearHabitacionDTO {
    private int numeroHabitacion;
    private String tipo;
    private double precio;
    private int idHotel;
}
