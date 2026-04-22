package com.proyecto.usuarios.repository;

import com.proyecto.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByNombreAndContrasena(String nombre, String contrasena);

    Optional<Usuario> findByNombre(String nombre);
}