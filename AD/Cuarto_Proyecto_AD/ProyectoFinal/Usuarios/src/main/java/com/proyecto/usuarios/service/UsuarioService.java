package com.proyecto.usuarios.service;

import com.proyecto.usuarios.dto.ActualizarUsuarioDTO;
import com.proyecto.usuarios.dto.CrearUsuarioDTO;
import com.proyecto.usuarios.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.usuarios.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    public String crearUsuario(CrearUsuarioDTO dto){
        try{
            Usuario usuario = new Usuario();
            usuario.setNombre(dto.getNombre());
            usuario.setCorreoElectronico(dto.getCorreoElectronico());
            usuario.setDireccion(dto.getDireccion());
            usuario.setContrasena(dto.getContrasena());

            usuarioRepository.save(usuario);
            return "USUARIO CREADO CORRECTAMENTE";
        }catch (Exception e){
            return "ERROR AL CREAR EL USUARIO";
        }
    }

    public String actualizarUsuario(ActualizarUsuarioDTO dto){
        if(usuarioRepository.existsById(dto.getUsuarioId())){
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(dto.getUsuarioId());
            usuario.setNombre(dto.getNombre());
            usuario.setCorreoElectronico(dto.getCorreoElectronico());
            usuario.setDireccion(dto.getDireccion());
            usuario.setContrasena(dto.getContrasena());

            usuarioRepository.save(usuario);
            return "USUARIO ACTUALIZADO CORRECTAMENTE";
        }
        return "USUARIO NO ENCONTRADO";
    }

    public String eliminarUsuario(String nombre, String contrasena){
        Optional<Usuario> usuarioEliminar = usuarioRepository.findByNombreAndContrasena(nombre,contrasena);
        if(usuarioEliminar.isPresent()){
            usuarioRepository.delete(usuarioEliminar.get());
            return "USUARIO ELIMINADO CORRECTAMENTE";
        }
        return "USUARIO O CONTRASEÑA INCORRECTOS";
    }

    public boolean validarUsuario(String nombre, String contrasena){
        return usuarioRepository.findByNombreAndContrasena(nombre,contrasena).isPresent();
    }

    public String obtenerInfoUsuarioPorId(Integer id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if(user.isPresent()){
            return user.get().getNombre();
        }
        return "USUARIO NO ENCONTRADO";
    }

    public String obtenerInfoUsuarioPorNombre(String nombre){
        Optional<Usuario> user = usuarioRepository.findByNombre(nombre);
        if(user.isPresent()){
            return  String.valueOf(user.get().getUsuarioId());
        }
        return "USUARIO NO ENCONTRADO";
    }

    public boolean checkIfExist(Integer id){
        return usuarioRepository.existsById(id);
    }
}