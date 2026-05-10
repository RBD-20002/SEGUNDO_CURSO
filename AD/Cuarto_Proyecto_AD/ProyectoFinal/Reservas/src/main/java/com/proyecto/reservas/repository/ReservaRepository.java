package com.proyecto.reservas.repository;

import com.proyecto.reservas.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Integer> {

    List<Reserva> findByUsuarioId(int id);

    List<Reserva> findByEstado(String estado);

    @Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.usuarioId =:idUsuario AND r.reservaId =:idReserva AND r.habitacion.hotel.hotelId =:idHotel")
    boolean checkReserva(int idUsuario, int idHotel, int idReserva);

    void deleteByHabitacion_HabitacionId(Integer habitacionId);
}