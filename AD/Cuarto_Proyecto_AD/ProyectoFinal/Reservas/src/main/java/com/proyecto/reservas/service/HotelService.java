package com.proyecto.reservas.service;

import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.ActualizarHotelDTO;
import com.proyecto.reservas.dto.CrearHotelDTO;
import com.proyecto.reservas.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.reservas.repository.HotelRepository;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public String crearHotel(CrearHotelDTO dto){
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

    public String actualizarHotel(ActualizarHotelDTO dto){

        Optional<Hotel> filtrado = hotelRepository.findById(dto.getId());
        if (filtrado.isPresent()) {
            Hotel hotel = filtrado.get();
            hotel.setHotelId(dto.getId());
            hotel.setNombre(dto.getNombre());
            hotel.setDireccion(dto.getDireccion());

            hotelRepository.save(hotel);
            return "ACTUALIZAR HOTEL FUE UN EXITO DESDE SERVICE";
        }
        return "ACTUALIZAR HOTEL FALLO EN SERVICE";
    }

    public String eliminarHotel(int id){
        Optional<Hotel> filtrado = hotelRepository.findById(id);
        if(filtrado.isPresent()){
            Hotel hotel = filtrado.get();
            hotelRepository.delete(hotel);
            return "ELIMINAR HOTEL FUE UN EXITO EN SERVICE";
        }
        return "ELIMINAR HOTEL FALLO EN SERVICE";
    }

    public String obtenerIdApartirNombre(String nombre){
        Optional<Hotel> filtrado = hotelRepository.findByNombre(nombre);
        if(filtrado.isPresent()){
            Hotel hotel = filtrado.get();
            return hotel.getHotelId()+" OBTENER ID A TRAVEZ DEL NOMBRE FUE UN EXITO";
        }
        return "OBTENER ID A TRAVEZ DEL NOMBRE FALLO DESDE SERVICE";
    }

    public String obtenerNombreAPartirId(int id){
        Optional<Hotel> filtrado = hotelRepository.findById(id);
        if(filtrado.isPresent()){
            Hotel hotel = filtrado.get();
            return hotel.getNombre()+" OBTENER NOMBRE A TRAVEZ DEL ID FUE UN EXITO";
        }
        return "OBTENER NOMBRE A TRAVEZ DE ID FALLO DESDE SERVICE";
    }
}
