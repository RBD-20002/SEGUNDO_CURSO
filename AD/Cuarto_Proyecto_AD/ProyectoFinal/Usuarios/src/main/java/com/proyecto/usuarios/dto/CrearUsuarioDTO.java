package com.proyecto.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearUsuarioDTO {
    private String nombre;
    private String correoElectronico;
    private String direccion;
    private String contrasena;
}