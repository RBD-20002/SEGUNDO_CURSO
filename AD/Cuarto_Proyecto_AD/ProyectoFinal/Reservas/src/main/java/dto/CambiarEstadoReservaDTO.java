package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambiarEstadoReservaDTO {
    private int reservaId;
    private String estado;
}