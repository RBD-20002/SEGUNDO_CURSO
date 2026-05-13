package com.proyecto.comentarios.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "reservas")
public interface ReservaFeignClient {

    @GetMapping("/reservas/hotel/id/{nombre}")
    String obtenerIdHotelPorNombre(@PathVariable("nombre") String nombre, @RequestParam String nombreUser, @RequestParam String contrasena);

    @GetMapping("/reservas/check")
    boolean checkReserva(@RequestParam int idUsuario, @RequestParam int idHotel, @RequestParam int idReserva);
}