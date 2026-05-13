package com.proyecto.comentarios.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuarios")
public interface UsuarioFeignClient {

    @PostMapping("/usuarios/validar")
    boolean validarUsuario(@RequestBody Object usuario);

    @GetMapping("/usuarios/info/nombre/{nombre}")
    String obtenerIdPorNombre(@PathVariable("nombre") String nombre);
}