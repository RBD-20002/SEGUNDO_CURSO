package com.proyecto.comentarios.controller;

import com.proyecto.comentarios.dto.ComentarioDTO;
import com.proyecto.comentarios.dto.UsuarioDTO;
import com.proyecto.comentarios.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @MutationMapping
    public ComentarioDTO crearComentario(@Argument String nombre, @Argument String contrasena, @Argument String nombreHotel, @Argument Integer reserva_id, @Argument Double puntuacion, @Argument String comentario){
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre,contrasena);
        ComentarioDTO comentarioDTO = new ComentarioDTO(nombreHotel, reserva_id, puntuacion, comentario);

        return comentarioService.crearComentario(comentarioDTO,usuarioDTO);
    }

    @MutationMapping
    public String eliminarComentarios(){
        comentarioService.eliminarComentarios();
        return "TODOOS LOS COMENTARIOS ELIMINADOS";
    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument String comentarioId){
        return comentarioService.eliminarComentarioDeUsuario(comentarioId);
    }

    @QueryMapping
    public List<ComentarioDTO> listarComentariosHotel(@Argument String nombreHotel, @Argument String nombre, @Argument String contrasena){
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre,contrasena);

        return comentarioService.listarComentariosHotel(nombreHotel,usuarioDTO);
    }

    @QueryMapping
    public List<ComentarioDTO> listarComentariosUsuario(@Argument String nombre, @Argument String contrasena){
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre,contrasena);

        return comentarioService.listarComentariosUsuario(usuarioDTO);
    }

    @QueryMapping
    public List<ComentarioDTO> mostrarComentarioUsuarioReserva(@Argument Integer reservaId, @Argument String nombre, @Argument String contrasena){
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre,contrasena);

        return comentarioService.mostrarComentarioUsuarioReserva(reservaId, usuarioDTO);
    }

    @QueryMapping
    public Double puntuacionMediaHotel(@Argument String nombreHotel, @Argument String nombre, @Argument String contrasena){
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre,contrasena);

        return comentarioService.puntuacionMediaHotel(nombreHotel,usuarioDTO);
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(@Argument String nombre, @Argument String contrasena){
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre,contrasena);

        return comentarioService.puntuacionesMediasUsuario(usuarioDTO);
    }
}