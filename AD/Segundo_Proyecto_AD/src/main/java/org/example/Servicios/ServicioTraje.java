package org.example.Servicios;

import org.example.Extra.EntradaDatos;
import org.example.Repositorios.RepoTraje;

public class ServicioTraje {

    private final RepoTraje repoTraje;

    public ServicioTraje(RepoTraje repoTraje){
        this.repoTraje = repoTraje;
    }

    public void cambiarTrajePersonaje() {

        repoTraje.listarTrajes();
        String nombrePersonaje = EntradaDatos.leerString("nombre del personaje tal cual");
        String nombreTraje = EntradaDatos.leerString("nombre del nuevo traje tal cual");

        repoTraje.cambiarTraje(nombrePersonaje, nombreTraje);
    }
}
