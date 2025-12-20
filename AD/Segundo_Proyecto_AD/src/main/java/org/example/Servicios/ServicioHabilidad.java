package org.example.Servicios;

import org.example.Extra.EntradaDatos;
import org.example.Repositorios.RepoHabilidad;
import org.example.Repositorios.RepoPersonaje;

public class ServicioHabilidad {

    private final RepoHabilidad repoHabilidad;
    private final RepoPersonaje repoPersonaje;

    public ServicioHabilidad(RepoHabilidad repoHabilidad, RepoPersonaje repoPersonaje) {
        this.repoHabilidad = repoHabilidad;
        this.repoPersonaje = repoPersonaje;
    }

    public void crearHabilidad() {
        repoHabilidad.listarHabilidades();
        int id = EntradaDatos.leerInt("id de habilidad");
        String nombre = EntradaDatos.leerString("nombre de habilidad");
        String descripcion = EntradaDatos.leerString("descripcion de habilidad");

        repoHabilidad.agregarHabilidad(id,nombre,descripcion);
    }

    public void eliminarHabilidad() {
        repoHabilidad.listarHabilidades();
        String nombre = EntradaDatos.leerString("nombre de habilidad tal cual");

        repoHabilidad.eliminarHabilidad(nombre);
    }

    public void modificarHabilidad() {
        repoHabilidad.listarHabilidades();
        int id = EntradaDatos.leerInt("id de habilidad a modificar");
        String nombre = EntradaDatos.leerString("nuevo nombre");
        String descripcion = EntradaDatos.leerString("nueva descripcion");

        repoHabilidad.modificarHabilidad(id,nombre,descripcion);
    }

    public void asignarHabilidad() {
        repoPersonaje.listarPersonajes();
        String nombrePersonaje = EntradaDatos.leerString("nombre del personaje tal cual");

        repoHabilidad.listarHabilidades();
        String nombreHabilidad = EntradaDatos.leerString("nombre de la habilidad tal cual");

        repoHabilidad.asignarHabilidad(nombrePersonaje,nombreHabilidad);
    }

    public void contarPorHabilidad() {
        repoHabilidad.listarHabilidades();
        String nombreHabildiad = EntradaDatos.leerString("nombre de habilidad tal cual");

        repoHabilidad.filtrarHabilidad(nombreHabildiad);
    }
}
