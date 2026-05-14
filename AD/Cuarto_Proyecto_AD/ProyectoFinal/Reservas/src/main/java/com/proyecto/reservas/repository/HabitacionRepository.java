package com.proyecto.reservas.repository;

import com.proyecto.reservas.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    List<Habitacion> findByHotel_HotelId(Integer hotelId);
}