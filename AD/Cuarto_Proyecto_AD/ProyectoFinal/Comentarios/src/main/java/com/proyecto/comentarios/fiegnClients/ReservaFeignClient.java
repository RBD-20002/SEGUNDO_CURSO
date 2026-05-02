package com.proyecto.comentarios.fiegnClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="reservas")
public interface ReservaFeignClient {

    @PostMapping("/reservas/hotel/id/{nombre}")
    String obtenerIdHotelPorNombre(@PathVariable String nombre, @RequestBody Object usuarioDTO);

    @GetMapping("/reservas/check")
    boolean checkReserva(@RequestParam int idUsuario, @RequestParam int idHotel, @RequestParam int idReserva);

    @PostMapping("/reservas/hotel/nombre/{id}")
    String obtenerNombreHotelPorId(@PathVariable Integer id, @RequestBody Object usuarioDTO);
}