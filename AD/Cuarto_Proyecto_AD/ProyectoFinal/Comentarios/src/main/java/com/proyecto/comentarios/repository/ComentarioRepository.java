package com.proyecto.comentarios.repository;

import com.proyecto.comentarios.entity.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends MongoRepository<Comentarios, String> {

    List<Comentarios> findByHotelId(int hotelId);

    List<Comentarios> findByUsuarioId(int usuarioId);

    List<Comentarios> findByReservaId(int reservaId);

    Optional<Comentarios> findByUsuarioIdAndHotelIdAndReservaId(Integer usuarioId, Integer hotelId, Integer reservaId);
}