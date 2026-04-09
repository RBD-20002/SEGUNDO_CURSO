package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarUsuarioDTO {
    private int usuarioId;
    private String nombre;
    private String correoElectronico;
    private String direccion;
    private String contrasena;
}