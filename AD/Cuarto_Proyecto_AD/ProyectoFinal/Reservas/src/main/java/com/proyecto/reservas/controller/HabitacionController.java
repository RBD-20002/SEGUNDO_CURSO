package com.proyecto.reservas.controller;

import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.CrearHabitacionDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas/habitacion")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    public String crearHabitacion(@RequestBody CrearHabitacionDTO dto, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return habitacionService.crearHabitacion(dto,usuario);
    }

    @PatchMapping
    public String actualizarHabitacion(@RequestBody ActualizarHabitacionDTO dto, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return habitacionService.actualizarHabitacion(dto, usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarHabitacion(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre,contrasena);
        return habitacionService.eliminarHabitacion(id, usuario);
    }
}