package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.CrearHabitacionDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.enums.TipoHabitacion;
import com.proyecto.reservas.feignClient.UsuarioFeignClient;
import com.proyecto.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.reservas.repository.HabitacionRepository;

import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    public String crearHabitacion(CrearHabitacionDTO dto, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        try{
            if(tipoValido(dto.getTipo())){
                Habitacion hab = new Habitacion();
                hab.setNumeroHabitacion(dto.getNumeroHabitacion());
                hab.setTipo(dto.getTipo());
                hab.setPrecio(dto.getPrecio());
                hab.setDisponible(true);

                Hotel hotel = new Hotel();
                hotel.setHotelId(dto.getIdHotel());
                hab.setHotel(hotel);

                habitacionRepository.save(hab);
                return "HABITACIÓN CREADA CORRECTAMENTE";
            }
            return "TIPO DE HABITACIÓN INVÁLIDO";
        }catch (Exception e){
            return "ERROR AL CREAR LA HABITACIÓN";
        }
    }

    public String actualizarHabitacion(ActualizarHabitacionDTO dto, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        Optional<Habitacion> filtrado = habitacionRepository.findById(dto.getId());
        if(filtrado.isPresent()){
            if(tipoValido(dto.getTipo())){
                Habitacion hab = filtrado.get();
                hab.setNumeroHabitacion(dto.getNumeroHabitacion());
                hab.setTipo(dto.getTipo());
                hab.setPrecio(dto.getPrecio());
                hab.setDisponible(dto.getDisponible());

                Hotel hotel = new Hotel();
                hotel.setHotelId(dto.getIdHotel());
                hab.setHotel(hotel);

                habitacionRepository.save(hab);
                return "HABITACIÓN ACTUALIZADA CORRECTAMENTE";
            }
            return "TIPO DE HABITACIÓN INVÁLIDO";
        }
        return "HABITACIÓN NO ENCONTRADA";
    }

    public String eliminarHabitacion(int id, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }

        boolean tieneReservas = reservaRepository.existsByHabitacion_HabitacionId(id);
        if(tieneReservas){
            return "NO SE PUEDE ELIMINAR: LA HABITACIÓN TIENE RESERVAS";
        }

        if(habitacionRepository.existsById(id)){
            habitacionRepository.deleteById(id);
            return "HABITACIÓN ELIMINADA CORRECTAMENTE";
        }
        return "HABITACIÓN NO ENCONTRADA";
    }

    public boolean tipoValido(String dato){
        for(TipoHabitacion tipo : TipoHabitacion.values()){
            if(tipo.name().equalsIgnoreCase(dato)){
                return true;
            }
        }
        return false;
    }
}