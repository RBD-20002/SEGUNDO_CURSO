package com.proyecto.comentarios.repository;

import com.proyecto.comentarios.dto.ComentarioDTO;
import com.proyecto.comentarios.dto.UsuarioDTO;
import com.proyecto.comentarios.entity.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentarios, String> {

    List<ComentarioDTO> listarComentariosHotel(String nombreHotel);

    List<ComentarioDTO> listarComentariosUsuario(UsuarioDTO usuario);

    List<ComentarioDTO> mostrarComentarioUsuarioReserva(Integer id);

    Double puntuacionMediaHotel(String nombre);

    Double puntuacionesMediasUsuario(UsuarioDTO usuario);
}