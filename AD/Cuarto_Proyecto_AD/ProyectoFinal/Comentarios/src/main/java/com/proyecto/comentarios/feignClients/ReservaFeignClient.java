package com.proyecto.comentarios.feignClients;

import com.proyecto.comentarios.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="reservas")
public interface ReservaFeignClient {

    @PostMapping("/reservas/hotel/id/{nombre}")
    String obtenerIdHotelPorNombre(@PathVariable String nombre, @RequestBody UsuarioDTO usuarioDTO);

    @GetMapping("/reservas/check")
    boolean checkReserva(@RequestParam int idUsuario, @RequestParam int idHotel, @RequestParam int idReserva);

    @PostMapping("/reservas/hotel/nombre/{id}")
    String obtenerNombreHotelPorId(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO);
}