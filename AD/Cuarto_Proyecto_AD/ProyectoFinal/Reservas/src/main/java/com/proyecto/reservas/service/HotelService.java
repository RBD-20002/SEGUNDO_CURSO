package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.ActualizarHotelDTO;
import com.proyecto.reservas.dto.CrearHotelDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.feignClient.UsuarioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.reservas.repository.HotelRepository;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    public String crearHotel(CrearHotelDTO dto, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        try{
            Hotel hotel = new Hotel();
            hotel.setNombre(dto.getNombre());
            hotel.setDireccion(dto.getDireccion());

            hotelRepository.save(hotel);
            return "CREAR HOTEL FUE UN EXITO EN SERVICE";
        }catch (Exception e){
            return "CREAR HOTEL SUFRIO UN FALLO EN SERVICE";
        }
    }

    public String actualizarHotel(ActualizarHotelDTO dto, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        Optional<Hotel> filtrado = hotelRepository.findById(dto.getHotelId());
        if (filtrado.isPresent()) {
            Hotel hotel = filtrado.get();
            hotel.setNombre(dto.getNombre());
            hotel.setDireccion(dto.getDireccion());

            hotelRepository.save(hotel);
            return "ACTUALIZAR HOTEL FUE UN EXITO DESDE SERVICE";
        }
        return "ACTUALIZAR HOTEL FALLO EN SERVICE";
    }

    public String eliminarHotel(int id, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        if(hotelRepository.existsById(id)){
            hotelRepository.deleteById(id);
            return "ELIMINAR HOTEL FUE UN EXITO EN SERVICE";
        }
        return "ELIMINAR HOTEL FALLO EN SERVICE";
    }

    public String obtenerIdApartirNombre(String nombre, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        Optional<Hotel> filtrado = hotelRepository.findByNombre(nombre);
        if(filtrado.isPresent()){
            Hotel hotel = filtrado.get();
            return String.valueOf(hotel.getHotelId());
        }
        return "OBTENER ID A TRAVEZ DEL NOMBRE FALLO DESDE SERVICE";
    }

    public String obtenerNombreAPartirId(int id, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }
        Optional<Hotel> filtrado = hotelRepository.findById(id);
        if(filtrado.isPresent()){
            Hotel hotel = filtrado.get();
            return hotel.getNombre();
        }
        return "OBTENER NOMBRE A TRAVEZ DE ID FALLO DESDE SERVICE";
    }
}
