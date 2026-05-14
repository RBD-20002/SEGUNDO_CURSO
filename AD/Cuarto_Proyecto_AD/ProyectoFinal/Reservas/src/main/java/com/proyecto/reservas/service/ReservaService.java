package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.CambiarEstadoReservaDTO;
import com.proyecto.reservas.dto.CrearReservaDTO;
import com.proyecto.reservas.dto.ReservaInfoDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.entity.Reserva;
import com.proyecto.reservas.feignClient.UsuarioFeignClient;
import com.proyecto.reservas.repository.HabitacionRepository;
import com.proyecto.reservas.repository.HotelRepository;
import com.proyecto.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    public String crearReserva(CrearReservaDTO reservaDTO, UsuarioDTO usuarioDTO) {
        if (!usuarioFeignClient.validarUsuario(usuarioDTO)) {
            return "USUARIO NO AUTORIZADO";
        }
        try {
            Optional<Habitacion> filtro = habitacionRepository.findById(reservaDTO.getHabitacionId());
            if (filtro.isPresent()) {
                Habitacion habitacion = filtro.get();
                if (!habitacion.isDisponible()) {
                    return "LA HABITACION NO ESTA DISPONIBLE";
                }

                String idUsuario = usuarioFeignClient.obtenerIdPorNombre(usuarioDTO.getNombre());
                Integer idParceado = Integer.parseInt(idUsuario);

                Reserva reserva = new Reserva();
                reserva.setUsuarioId(idParceado);
                reserva.setHabitacion(habitacion);
                reserva.setFechaInicio(reservaDTO.getFechaInicio());
                reserva.setFechaFin(reservaDTO.getFechaFin());
                reserva.setEstado("CONFIRMADO");

                habitacion.setDisponible(false);
                habitacionRepository.save(habitacion);

                reservaRepository.save(reserva);
                return "RESERVA CREADA CORRECTAMENTE";
            }
            return "HABITACION NO ENCONTRADA";
        } catch (Exception e) {
            return "ERROR AL CREAR LA RESERVA";
        }
    }

    public String cambiarEstado(CambiarEstadoReservaDTO estadoDTO, UsuarioDTO usuarioDTO) {
        if (!usuarioFeignClient.validarUsuario(usuarioDTO)) {
            return "USUARIO NO AUTORIZADO";
        }
        Optional<Reserva> filtro = reservaRepository.findById(estadoDTO.getReservaId());
        if (filtro.isPresent()) {
            Reserva reserva = filtro.get();
            reserva.setEstado(estadoDTO.getEstado());

            Habitacion habitacion = reserva.getHabitacion();
            if (habitacion != null && (estadoDTO.getEstado().equalsIgnoreCase("CANCELADA") || estadoDTO.getEstado().equalsIgnoreCase("PAGADA"))) {
                habitacion.setDisponible(true);
                habitacionRepository.save(habitacion);
            }

            reservaRepository.save(reserva);
            return "ESTADO DE LA RESERVA ACTUALIZADO";
        }
        return "RESERVA NO ENCONTRADA";
    }

    public List<ReservaInfoDTO> listarReservasUsuario(UsuarioDTO usuarioDTO) {
        if (!usuarioFeignClient.validarUsuario(usuarioDTO)) {
            return null;
        }

        String idUsuario = usuarioFeignClient.obtenerIdPorNombre(usuarioDTO.getNombre());
        Integer idParceado = Integer.parseInt(idUsuario);

        List<Reserva> reservas = reservaRepository.findByUsuarioId(idParceado);
        return convertirAListaDTO(reservas);
    }

    public List<ReservaInfoDTO> listarReservasSegunEstado(String estado, UsuarioDTO usuarioDTO) {
        if (!usuarioFeignClient.validarUsuario(usuarioDTO)) {
            return null;
        }
        List<Reserva> reservas = reservaRepository.findByEstado(estado);
        return convertirAListaDTO(reservas);
    }

    private List<ReservaInfoDTO> convertirAListaDTO(List<Reserva> reservas) {
        List<ReservaInfoDTO> listaFinal = new ArrayList<>();
        for (Reserva r : reservas) {
            ReservaInfoDTO info = new ReservaInfoDTO();
            info.setFechaInicio(r.getFechaInicio());
            info.setFechaFin(r.getFechaFin());
            info.setHabitacionId(r.getHabitacion().getHabitacionId());
            listaFinal.add(info);
        }
        return listaFinal;
    }

    public boolean checkReserva(int idUsuario, int idHotel, int idReserva) {
        return reservaRepository.checkReserva(idUsuario, idHotel, idReserva);
    }

    public String obtenerIdHotelPorNombre(String nombre, UsuarioDTO usuarioDTO){
        if (!usuarioFeignClient.validarUsuario(usuarioDTO)) {
            throw new RuntimeException("USUARIO NO AUTORIZADO");
        }
        Optional<Hotel> hotel = hotelRepository.findByNombre(nombre);

        if(!hotel.isPresent()){
            throw new RuntimeException("HOTEL NO ENCONTRADO");
        }
        return String.valueOf(hotel.get().getHotelId());
    }

    public String obtenerNombreHotelPorId(Integer id, UsuarioDTO usuarioDTO){
        if (!usuarioFeignClient.validarUsuario(usuarioDTO)) {
            throw new RuntimeException("USUARIO NO AUTORIZADO");
        }
        Optional<Hotel> hotel = hotelRepository.findById(id);

        if(!hotel.isPresent()){
            throw new RuntimeException("HOTEL NO ENCONTRADO");
        }
        return hotel.get().getNombre();
    }
}