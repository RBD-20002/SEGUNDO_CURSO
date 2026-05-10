package com.proyecto.usuarios.controller;

import com.proyecto.usuarios.dto.ActualizarUsuarioDTO;
import com.proyecto.usuarios.dto.CrearUsuarioDTO;
import com.proyecto.usuarios.dto.EliminarAndValidarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.proyecto.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @PostMapping("/registrar")
    public String crearUsuario(@RequestBody CrearUsuarioDTO usuario){
        return usuarioService.crearUsuario(usuario);
    }

    @PutMapping("/registrar")
    public String actualizarUsuario(@RequestBody ActualizarUsuarioDTO usuario){
        return usuarioService.actualizarUsuario(usuario);
    }

    @DeleteMapping
    public String eliminarUsuario(@RequestBody EliminarAndValidarDTO usuario){
        return usuarioService.eliminarUsuario(usuario.getNombre(), usuario.getContrasena());
    }

    @PostMapping("/validar")
    public boolean validarUsuario(@RequestBody EliminarAndValidarDTO usuario){
        return usuarioService.validarUsuario(usuario.getNombre(), usuario.getContrasena());
    }

    @GetMapping("/info/id/{id}")
    public String obtenerInfoUsuarioPorId(@PathVariable Integer id){
        return usuarioService.obtenerInfoUsuarioPorId(id);
    }

    @GetMapping("/info/nombre/{nombre}")
    public String obtenerInfoUsuarioPorNombre(@PathVariable String nombre){
        return usuarioService.obtenerInfoUsuarioPorNombre(nombre);
    }

    @GetMapping("/checkIfExist/{id}")
    public boolean checkIfExist(@PathVariable Integer id){
        return usuarioService.checkIfExist(id);
    }
}