package com.proyecto.comentarios.controller;

import com.proyecto.comentarios.dto.CrearComentarioDTO;
import com.proyecto.comentarios.entity.Comentarios;
import com.proyecto.comentarios.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ComentarioController {

    @Autowired
    private ComentariosService comentariosService;

    @MutationMapping
    public CrearComentarioDTO crearComentario(@Argument CrearComentarioDTO input) {
        return comentariosService.crearComentario(input);
    }

    @MutationMapping
    public String eliminarTodos() {
        return comentariosService.eliminarTodos();
    }

    @MutationMapping
    public String eliminarComentario(@Argument String id) {
        return comentariosService.eliminarComentario(id);
    }

    @QueryMapping
    public List<Comentarios> listarComentariosHotel(@Argument Integer hotelId) {
        return comentariosService.listarComentariosHotel(hotelId);
    }

    @QueryMapping
    public List<Comentarios> listarComentariosUsuario(@Argument Integer usuarioId) {
        return comentariosService.listarComentariosUsuario(usuarioId);
    }

    @QueryMapping
    public List<Comentarios> mostrarComentarioUsuarioReserva(@Argument Integer reservaId) {
        return comentariosService.mostrarComentarioUsuarioReserva(reservaId);
    }

    @QueryMapping
    public Double puntuacionMediaHotel(@Argument Integer hotelId) {
        return comentariosService.puntuacionMediaHotel(hotelId);
    }

    @QueryMapping
    public Double puntuacionMediaUsuario(@Argument Integer usuarioId) {
        return comentariosService.puntuacionMediaUsuario(usuarioId);
    }
}