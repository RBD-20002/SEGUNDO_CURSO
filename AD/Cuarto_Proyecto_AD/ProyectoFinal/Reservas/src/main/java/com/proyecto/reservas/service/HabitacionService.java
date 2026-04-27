package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.CrearHabitacionDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.enums.TipoHabitacion;
import com.proyecto.reservas.feignClient.UsuarioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.reservas.repository.HabitacionRepository;

import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

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
                return "CREACION DE HABITACION FUE UN EXITO DESDE SERVICE";
            }
            return "CREACION DE HABITACION FALLO PORQUE EL TIPO ES INVALIDO";
        }catch (Exception e){
            return "CREAR HABITACION SUFRIO UN FALLO EN SERVICE";
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
                return "ACTUALIZAR HABITACION FUE UN EXITO DESDE SERVICE";
            }
            return "ACTUALIZAR HABITACION FALLO POR TIPO INVALIDO";
        }
        return "ACTUALIZAR HABITACION FALLO DESDE SERVICE";
    }

    public String eliminarHabitacion(int id, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        if(habitacionRepository.existsById(id)){
            habitacionRepository.deleteById(id);
            return "ELIMINAR HABITACION FUE UN EXITO EN SERVICE";
        }
        return "ELIMINAR HABITACION FALLO EN SERVICE";
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