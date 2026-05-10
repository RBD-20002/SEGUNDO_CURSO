package com.proyecto.comentarios.service;

import com.proyecto.comentarios.dto.ComentarioDTO;
import com.proyecto.comentarios.dto.UsuarioDTO;
import com.proyecto.comentarios.entity.Comentarios;
import com.proyecto.comentarios.feignClients.ReservaFeignClient;
import com.proyecto.comentarios.feignClients.UsuarioFeignClient;
import com.proyecto.comentarios.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ReservaFeignClient reservaFeignClient;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    public ComentarioDTO crearComentario(ComentarioDTO comentarioDTO, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            throw new RuntimeException("USUARIO NO ENCONTRADO");
        }

        Integer usuarioId = Integer.parseInt(usuarioFeignClient.obtenerIdPorNombre(usuarioDTO.getNombre()));
        Integer hotelId = Integer.parseInt(reservaFeignClient.obtenerIdHotelPorNombre(comentarioDTO.getNombreHotel(), usuarioDTO));

        if(!reservaFeignClient.checkReserva(usuarioId,hotelId,comentarioDTO.getReserva_id())){
            throw new RuntimeException("NO EXISTE UNA RESERVA VALIDA PARA ESE USAURIO");
        }

        if(comentarioRepository.existsByUsuarioIdAndHotelIdAndReservaId(usuarioId, hotelId, comentarioDTO.getReserva_id())){
            throw new RuntimeException("EL USUARIO YA HIZO UN COMENTARIO");
        }

        Comentarios nuevoComentario = new Comentarios();
            nuevoComentario.setUsuarioId(usuarioId);
            nuevoComentario.setHotelId(hotelId);
            nuevoComentario.setReservaId(comentarioDTO.getReserva_id());
            nuevoComentario.setPuntuacion(comentarioDTO.getPuntuacion());
            nuevoComentario.setComentario(comentarioDTO.getComentario());

            comentarioRepository.save(nuevoComentario);
            return comentarioDTO;
    }

    public String eliminarComentarios(){
        comentarioRepository.deleteAll();
        return "SE ELIMINO TODOS COMENTARIOS";
    }

    public String eliminarComentarioDeUsuario(String comentarioId){
        comentarioRepository.deleteById(String.valueOf(comentarioId));
        return "COMENTARIO DE USUARIO ELIMINADO";
    }

    public List<ComentarioDTO> listarComentariosHotel(String nombreHotel, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            throw new RuntimeException("USUARIO NO ENCONTRADO");
        }
        Integer hotelId = Integer.parseInt(reservaFeignClient.obtenerIdHotelPorNombre(nombreHotel, usuarioDTO));
        List<Comentarios> comentarios = comentarioRepository.findByHotelId(hotelId);
        List<ComentarioDTO> comentarioDTOS = new ArrayList<>();

        for(Comentarios comentario : comentarios){
            ComentarioDTO comentarioDTO = new ComentarioDTO();

            comentarioDTO.setNombreHotel(nombreHotel);
            comentarioDTO.setReserva_id(comentario.getReservaId());
            comentarioDTO.setPuntuacion(comentario.getPuntuacion());
            comentarioDTO.setComentario(comentario.getComentario());

            comentarioDTOS.add(comentarioDTO);
        }
        return comentarioDTOS;
    }

    public List<ComentarioDTO> listarComentariosUsuario(UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            throw new RuntimeException("USUARIO NO ENCONTRADO");
        }
        Integer usuarioId = Integer.parseInt(usuarioFeignClient.obtenerIdPorNombre(usuarioDTO.getNombre()));
        List<Comentarios> comentariosUsuarios = comentarioRepository.findByUsuarioId(usuarioId);
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();

        for(Comentarios c : comentariosUsuarios){
            ComentarioDTO dto = new ComentarioDTO();
            dto.setNombreHotel(reservaFeignClient.obtenerNombreHotelPorId(c.getHotelId(),usuarioDTO));
            dto.setReserva_id(c.getReservaId());
            dto.setPuntuacion(c.getPuntuacion());
            dto.setComentario(c.getComentario());

            comentariosDTO.add(dto);
        }
        return comentariosDTO;
    }

    public List<ComentarioDTO> mostrarComentarioUsuarioReserva(Integer reservaId, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            throw new RuntimeException("USUARIO NO VALIDO");
        }
        Integer usuarioID = Integer.parseInt(usuarioFeignClient.obtenerIdPorNombre(usuarioDTO.getNombre()));

        List<Comentarios> comentarios = comentarioRepository.findByUsuarioIdAndReservaId(usuarioID,reservaId);
        if(comentarios.isEmpty()) return null;

        List<ComentarioDTO> comentarioDTO = new ArrayList<>();
        for(Comentarios c : comentarios){
            ComentarioDTO dto = new ComentarioDTO();
            dto.setNombreHotel(reservaFeignClient.obtenerNombreHotelPorId(c.getHotelId(),usuarioDTO));
            dto.setReserva_id(c.getReservaId());
            dto.setPuntuacion(c.getPuntuacion());
            dto.setComentario(c.getComentario());

            comentarioDTO.add(dto);
        }
        return comentarioDTO;
    }

    public Double puntuacionMediaHotel(String nombre, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            throw new RuntimeException("USUARIO NO ENCONTRADO");
        }
        Integer hotelId = Integer.parseInt(reservaFeignClient.obtenerIdHotelPorNombre(nombre, usuarioDTO));

        List<Comentarios> comentarios = comentarioRepository.findByHotelId(hotelId);
        if(comentarios.isEmpty()) return 0.0;

        Double suma = 0.0;
        for(Comentarios comentario : comentarios){
            suma += comentario.getPuntuacion();
        }
        return suma / comentarios.size();
    }

    public Double puntuacionesMediasUsuario(UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            throw new RuntimeException("USUARIO NO VALIDO");
        }
        Integer usuarioId = Integer.parseInt(usuarioFeignClient.obtenerIdPorNombre(usuarioDTO.getNombre()));

        List<Comentarios> comentarios = comentarioRepository.findByUsuarioId(usuarioId);
        if(comentarios.isEmpty()) return 0.0;

        Double suma = 0.0;
        for(Comentarios c : comentarios){
            suma += c.getPuntuacion();
        }
        return suma / comentarios.size();
    }
}