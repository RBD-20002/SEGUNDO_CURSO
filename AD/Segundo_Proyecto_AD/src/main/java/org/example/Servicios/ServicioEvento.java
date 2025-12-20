package org.example.Servicios;

import org.example.Extra.EntradaDatos;
import org.example.Repositorios.RepoEvento;
import org.example.Repositorios.RepoParticipa;
import org.example.Repositorios.RepoPersonaje;
import java.util.Date;

public class ServicioEvento {

    private final RepoEvento repoEvento;
    private final RepoParticipa repoParticipa;
    private final RepoPersonaje repoPersonaje;

    public ServicioEvento(RepoEvento repoEvento, RepoParticipa repoParticipa, RepoPersonaje repoPersonaje) {
        this.repoEvento = repoEvento;
        this.repoParticipa = repoParticipa;
        this.repoPersonaje = repoPersonaje;
    }

    public void registrarParticipacion() {
        repoPersonaje.listarPersonajes();
        String nomPersonaje = EntradaDatos.leerString("nombre del personaje tal cual");

        repoEvento.listarEvento();
        String nomEvento = EntradaDatos.leerString("nombre del evento tal cual");

        Date fecha = EntradaDatos.leerDate("la fecha de cuando paso el evento");

        String rol = EntradaDatos.leerString("el rol que cumplio el personaje");

        repoParticipa.registrarParticipacion(nomPersonaje,nomEvento,rol,fecha);
    }

    public void filtrarPersonajesPorEvento() {
        repoEvento.listarEvento();
        String nombreEvento = EntradaDatos.leerString("nombre del evento tal cual");

        repoEvento.filtrarPorEvento(nombreEvento);
    }
}
