package com.proyecto.comentarios.service;

import com.proyecto.comentarios.dto.CrearComentarioDTO;
import com.proyecto.comentarios.dto.UsuarioDTO;
import com.proyecto.comentarios.entity.Comentarios;
import com.proyecto.comentarios.feignClients.ReservaFeignClient;
import com.proyecto.comentarios.feignClients.UsuarioFeignClient;
import com.proyecto.comentarios.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentariosService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    @Autowired
    private ReservaFeignClient reservaFeignClient;

    public CrearComentarioDTO crearComentario(CrearComentarioDTO dto) {
        UsuarioDTO usuario = new UsuarioDTO(dto.getNombreUsuario(), dto.getContrasena());
        if (!usuarioFeignClient.validarUsuario(usuario)) {
            throw new RuntimeException("USUARIO NO AUTORIZADO");
        }

        String usuarioIdStr = usuarioFeignClient.obtenerIdPorNombre(dto.getNombreUsuario());
        Integer usuarioId = Integer.parseInt(usuarioIdStr);
        String hotelIdStr = reservaFeignClient.obtenerIdHotelPorNombre(dto.getNombreHotel(), dto.getNombreUsuario(), dto.getContrasena());
        Integer hotelId = Integer.parseInt(hotelIdStr);
        boolean existe = reservaFeignClient.checkReserva(usuarioId, hotelId, dto.getReservaId());

        if (!existe) {
            throw new RuntimeException("RESERVA NO VALIDA");
        }

        boolean duplicado = comentarioRepository.findByUsuarioIdAndHotelIdAndReservaId(usuarioId, hotelId, dto.getReservaId()).isPresent();
        if (duplicado) {
            throw new RuntimeException("YA EXISTE COMENTARIO");
        }

        Comentarios comentario = new Comentarios(usuarioId, hotelId, dto.getReservaId(), dto.getPuntuacion(), dto.getComentario(), java.time.LocalDateTime.now());
        comentarioRepository.save(comentario);
        return dto;
    }

    public List<Comentarios> listarComentariosHotel(int hotelId) {
        return comentarioRepository.findByHotelId(hotelId);
    }

    public List<Comentarios> listarComentariosUsuario(Integer usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    public List<Comentarios> mostrarComentarioUsuarioReserva(Integer reservaId) {
        return comentarioRepository.findByReservaId(reservaId);
    }

    public String eliminarTodos() {
        comentarioRepository.deleteAll();
        return "ELIMINADOS TODOS LOS COMENTARIOS";
    }

    public String eliminarComentario(String id) {
        comentarioRepository.deleteById(id);
        return "COMENTARIO ELIMINADO";
    }

    public Double puntuacionMediaHotel(Integer hotelId) {
        List<Comentarios> lista = comentarioRepository.findByHotelId(hotelId);
        return lista.stream()
                .mapToDouble(Comentarios::getPuntuacion)
                .average()
                .orElse(0.0);
    }

    public Double puntuacionMediaUsuario(Integer usuarioId) {
        List<Comentarios> lista = comentarioRepository.findByUsuarioId(usuarioId);
        return lista.stream()
                .mapToDouble(Comentarios::getPuntuacion)
                .average()
                .orElse(0.0);
    }
}