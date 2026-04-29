package com.proyecto.comentarios.fiegnClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="reservas")
public interface ReservaFeignClient {

    @PostMapping("/usuarios/validar")
    boolean validarUsuario(@RequestParam("nombre") String nombre, @RequestParam("contrasena") String contrasena);

    @GetMapping("/usuarios/info/nombre/{nombre}")
    String obtenerInfoUsuarioPorNombre(@PathVariable("nombre") String nombre);
}