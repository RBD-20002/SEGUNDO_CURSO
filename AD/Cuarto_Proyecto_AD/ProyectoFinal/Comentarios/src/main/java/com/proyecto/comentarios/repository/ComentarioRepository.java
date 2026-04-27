package com.proyecto.comentarios.repository;

import com.proyecto.comentarios.entity.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentarios, String> {

    String eliminarComentarioDeUsuario(Integer id);

    List<Comentarios> listarComentariosHotel(String nombreHotel);

    List<Comentarios> listarComentariosUsuario();
}