package com.proyecto.reservas.service;

import com.proyecto.reservas.Usuario.UsuarioValidador;
import com.proyecto.reservas.dto.ActualizarHabitacionDTO;
import com.proyecto.reservas.dto.CrearHabitacionDTO;
import com.proyecto.reservas.entity.Habitacion;
import com.proyecto.reservas.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.reservas.repository.HabitacionRepository;

import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    public HabitacionRepository habitacionRepository;

    @Autowired
    public UsuarioValidador usuarioValidador;

    public String crearHabitacion(CrearHabitacionDTO dto){
        try{
            Habitacion hab = new Habitacion();
            hab.setNumeroHabitacion(dto.getNumeroHabitacion());
            hab.setTipo(dto.getTipo());
            hab.setPrecio(dto.getPrecio());
            hab.setDisponible(true);

            Hotel hotel = new Hotel();
            hotel.setHotelId(dto.getIdHotel());

            hab.setHotelId(hotel);
            habitacionRepository.save(hab);
            return "CREACION DE HABITACION FUE UN EXITO DESDE SERVICE";
        }catch (Exception e){
            return "CREAR HABITACION SUFRIO UN FALLO EN SERVICE";
        }
    }

    public String actualizarHabitacion(ActualizarHabitacionDTO dto){
        Optional<Habitacion> filtrado = habitacionRepository.findById(dto.getId());
        if(filtrado.isPresent()){
            Habitacion hab = filtrado.get();
            hab.setNumeroHabitacion(dto.getNumeroHabitacion());
            hab.setTipo(dto.getTipo());
            hab.setPrecio(dto.getPrecio());
            hab.setDisponible(dto.getDisponible());

            Hotel hotel = new Hotel();
            hotel.setHotelId(dto.getId());
            hab.setHotelId(hotel);
            habitacionRepository.save(hab);
            return "ACTUALIZAR HABITACION FUE UN EXITO DESDE SERVICE";
        }
        return "ACTUALIZAR HABITACION FALLO DESDE SERVICE";
    }

    public String eliminarHabitacion(int id){
        Optional<Habitacion> hab = habitacionRepository.findById(id);
        if(hab.isPresent()){
            Habitacion eliminar = hab.get();
            habitacionRepository.delete(eliminar);
            return "ELIMINAR HABITACION FUE UN EXITO EN SERVICE";
        }
        return "ELIMINAR HABITACION FALLO EN SERVICE";
    }
}