package com.proyecto.reservas.controller;

import com.proyecto.reservas.dto.CambiarEstadoReservaDTO;
import com.proyecto.reservas.dto.CrearReservaDTO;
import com.proyecto.reservas.dto.ReservaInfoDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public String crearReserva(@RequestBody CrearReservaDTO dto, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre, contrasena);
        return reservaService.crearReserva(dto,usuario);
    }

    @PatchMapping
    public String cambiarEstado(@RequestBody CambiarEstadoReservaDTO dto, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre, contrasena);
        return reservaService.cambiarEstado(dto,usuario);
    }

    @GetMapping
    public List<ReservaInfoDTO> listarReservasUsuario(@RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre, contrasena);
        return reservaService.listarReservasUsuario(usuario);
    }

    @GetMapping("/{estado}")
    public List<ReservaInfoDTO> listarReservasSegunEstado(@PathVariable String estado, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre, contrasena);
        return reservaService.listarReservasSegunEstado(estado,usuario);
    }

    @GetMapping("/check")
    public boolean checkReserva(@RequestParam int idUsuario, @RequestParam int idHotel, @RequestParam int idReserva) {
        return reservaService.checkReserva(idUsuario, idHotel, idReserva);
    }

    @GetMapping("/hotel/id/{nombre}")
    public String obtenerIdHotelPorNombre(@PathVariable String nombre, @RequestParam String nombreUser, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombreUser, contrasena);
        return reservaService.obtenerIdHotelPorNombre(nombre,usuario);
    }

    @GetMapping("/hotel/nombre/{id}")
    public String obtenerNombreHotelPorId(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String contrasena){
        UsuarioDTO usuario = new UsuarioDTO(nombre, contrasena);
        return reservaService.obtenerNombreHotelPorId(id,usuario);
    }
}