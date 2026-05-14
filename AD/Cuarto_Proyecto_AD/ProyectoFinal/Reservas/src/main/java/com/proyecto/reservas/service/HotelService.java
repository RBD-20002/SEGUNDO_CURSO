package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.ActualizarHotelDTO;
import com.proyecto.reservas.dto.CrearHotelDTO;
import com.proyecto.reservas.dto.UsuarioDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import com.proyecto.reservas.feignClient.UsuarioFeignClient;
import com.proyecto.reservas.repository.HabitacionRepository;
import com.proyecto.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.reservas.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

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
            return "HOTEL CREADO CORRECTAMENTE";
        }catch (Exception e){
            return "ERROR AL CREAR EL HOTEL";
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
            return "HOTEL ACTUALIZADO CORRECTAMENTE";
        }
        return "HOTEL NO ENCONTRADO";
    }

    public String eliminarHotel(int id, UsuarioDTO usuarioDTO){
        if(!usuarioFeignClient.validarUsuario(usuarioDTO)){
            return "USUARIO NO AUTORIZADO";
        }

        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent()){
            List<Habitacion> habitaciones = habitacionRepository.findByHotel_HotelId(id);
            for(Habitacion habitacion : habitaciones){
                reservaRepository.deleteByHabitacion_HabitacionId(habitacion.getHabitacionId());
                habitacionRepository.delete(habitacion);
            }

            hotelRepository.delete(hotel.get());
            return "HOTEL ELIMINADO CORRECTAMENTE";
        }

        return "HOTEL NO SE PUDO ELIMINAR PORQUE NO SE ENCONTRO";
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
        return "HOTEL NO ENCONTRADO PARA MOSTRAR LA ID";
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
        return "HOTEL NO ENCONTRADO PARA MOSTRAR EL NOMBRE";
    }
}
