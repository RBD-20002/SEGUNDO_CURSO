package com.proyecto.comentarios.fiegnClients;

import com.proyecto.comentarios.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuarios")
public interface UsuarioFeignClient {

    @PostMapping("/usuarios/validar")
    boolean validarUsuario(@RequestBody UsuarioDTO usuario);

    @GetMapping("/usuarios/info/nombre/{nombre}")
    String obtenerIdPorNombre(@PathVariable("nombre") String nombre);
}