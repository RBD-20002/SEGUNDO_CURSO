package com.proyecto.reservas.Usuario;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Usuarios", url="http://localhost:8500/eureka")
public interface UsuarioValidador {

    @PostMapping("/usuarios/validar")
    boolean validarUsuario(@RequestParam("nombre") String nombre, @RequestParam("contrasena") String contrasena);
}