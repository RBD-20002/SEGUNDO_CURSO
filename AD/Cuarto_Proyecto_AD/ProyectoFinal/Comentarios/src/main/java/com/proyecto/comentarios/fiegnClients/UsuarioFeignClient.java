package com.proyecto.comentarios.fiegnClients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "usuarios")
public interface UsuarioFeignClient {
}
