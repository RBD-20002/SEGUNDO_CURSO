package com.proyecto.reservas.repository;

import com.proyecto.reservas.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Integer> {

    List<Reserva> findByUsuarioId(int id);

    List<Reserva> findByEstado(String estado);

    boolean existsByReservaIdAndUsuarioIdAndHabitacionId_Hotel_HotelId(int idUsuario, int idHotel, int idReserva);
}