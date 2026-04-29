package com.proyecto.reservas.controller;

import com.proyecto.reservas.dto.ActualizarHotelDTO;
import com.proyecto.reservas.dto.CrearHotelDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/")
    public String crearHotel(@RequestBody CrearHotelDTO dto, @RequestBody UsuarioDTO usuario){
        return hotelService.crearHotel(dto, usuario);
    }

    @PatchMapping("/")
    public String actualizarHotel(@RequestBody ActualizarHotelDTO dto, @RequestBody UsuarioDTO usuario){
        return hotelService.actualizarHotel(dto, usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarHotel(@PathVariable Integer id, @RequestBody UsuarioDTO usuario){
        return hotelService.eliminarHotel(id, usuario);
    }

    @PostMapping("/id/{nombre}")
    public String obtenerIdApartirNombre(@PathVariable String nombre, @RequestBody UsuarioDTO usuario){
        return hotelService.obtenerIdApartirNombre(nombre,usuario);
    }

    @PostMapping("/nombre/{id}")
    public String obtenerNombreAPartirId(@PathVariable Integer id, @RequestBody UsuarioDTO usuario){
        return hotelService.obtenerNombreAPartirId(id,usuario);
    }
}