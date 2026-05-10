package com.proyecto.comentarios.repository;

import com.proyecto.comentarios.dto.ComentarioDTO;
import com.proyecto.comentarios.dto.UsuarioDTO;
import com.proyecto.comentarios.entity.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentarios, String> {
    List<Comentarios> findByHotelId(Integer hotelId);

    List<Comentarios> findByUsuarioId(Integer usuarioId);

    List<Comentarios> findByUsuarioIdAndReservaId(Integer usuarioId, Integer reservaId);

    boolean existsByUsuarioIdAndHotelIdAndReservaId(Integer usuarioId, Integer hotelId, Integer reservaId);
}