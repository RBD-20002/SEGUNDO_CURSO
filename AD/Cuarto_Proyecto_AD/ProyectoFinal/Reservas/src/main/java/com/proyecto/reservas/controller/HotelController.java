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

    @PostMapping
    public String crearHotel(@RequestBody CrearHotelDTO dto, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return hotelService.crearHotel(dto, usuario);
    }

    @PatchMapping
    public String actualizarHotel(@RequestBody ActualizarHotelDTO dto, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return hotelService.actualizarHotel(dto, usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarHotel(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return hotelService.eliminarHotel(id, usuario);
    }

    @PostMapping("/id/{nombreHotel}")
    public String obtenerIdApartirNombre(@PathVariable String nombreHotel, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return hotelService.obtenerIdApartirNombre(nombreHotel,usuario);
    }

    @PostMapping("/nombre/{id}")
    public String obtenerNombreAPartirId(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return hotelService.obtenerNombreAPartirId(id,usuario);
    }
}