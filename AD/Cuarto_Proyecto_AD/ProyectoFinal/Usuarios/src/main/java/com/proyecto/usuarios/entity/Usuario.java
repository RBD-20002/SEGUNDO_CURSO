package com.proyecto.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private int usuarioId;

    @Column(name = "nombre", columnDefinition = "VARCHAR(100) DEFAULT NULL", length = 100)
    private String nombre;

    @Column(name = "correo_electronico", columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private String correoElectronico;

    @Column(name = "direccion", columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private String direccion;

    @Column(name = "contrasena", columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private String contrasena;
}