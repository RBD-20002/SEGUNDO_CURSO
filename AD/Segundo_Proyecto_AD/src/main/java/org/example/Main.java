package org.example;

import org.example.Extra.EntradaDatos;
import org.example.Extra.Menus;
import org.example.Repositorios.*;
import org.example.Servicios.ServicioEvento;
import org.example.Servicios.ServicioHabilidad;
import org.example.Servicios.ServicioPersonaje;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();
        RepoPersonaje repoPersonaje = new RepoPersonaje(session);
        RepoHabilidad repoHabilidad = new RepoHabilidad(session);
        RepoTraje repoTraje = new RepoTraje(session);
        RepoEvento repoEvento = new RepoEvento(session);
        RepoParticipa repoParticipa = new RepoParticipa(session);

        ServicioPersonaje servicioPersonaje = new ServicioPersonaje(repoPersonaje, repoTraje);
        ServicioHabilidad servicioHabilidad = new ServicioHabilidad(repoHabilidad, repoPersonaje);
        ServicioEvento servicioEvento = new ServicioEvento(repoEvento, repoParticipa, repoPersonaje);

        int opcion;
        do {
            Menus.menu1();
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1 -> servicioPersonaje.crearPersonaje();
                case 2 -> servicioPersonaje.eliminarPersonaje();
                case 3 -> servicioPersonaje.modificarPersonaje();
                case 4 -> servicioHabilidad.crearHabilidad();
                case 5 -> servicioHabilidad.eliminarHabilidad();
                case 6 -> servicioHabilidad.modificarHabilidad();
                case 7 -> servicioHabilidad.asignarHabilidad();
                case 8 -> servicioEvento.registrarParticipacion();
                case 9 -> System.out.println("as");
                case 10 -> servicioPersonaje.mostrarDatosPersonaje();
                case 11 -> servicioEvento.filtrarPersonajesPorEvento();
                case 12 -> servicioHabilidad.contarPorHabilidad();
                case 13 -> System.out.println("╔"+"═".repeat(18)+"╗"+"\n║ ADIOS........... ║\n╚"+"═".repeat(18)+"╝");
                default -> System.out.println("╔"+"═".repeat(17)+"╗"+"\n║ OPCION INVALIDA ║\n╚"+"═".repeat(17)+"╝");
            }
        }while (opcion !=13);
        session.close();
    }
}