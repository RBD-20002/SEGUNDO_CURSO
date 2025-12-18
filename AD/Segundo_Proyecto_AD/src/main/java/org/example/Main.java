package org.example;

import org.example.Entidades.Habilidad;
import org.example.Entidades.Personaje;
import org.example.Entidades.Traje;
import org.example.Extra.EntradaDatos;
import org.example.Extra.Menus;
import org.example.Repositorios.RepoEvento;
import org.example.Repositorios.RepoHabilidad;
import org.example.Repositorios.RepoPersonaje;
import org.example.Repositorios.RepoTraje;
import org.hibernate.Session;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();
        RepoPersonaje repoPersonaje = new RepoPersonaje(session);
        RepoHabilidad repoHabilidad = new RepoHabilidad(session);
        RepoTraje repoTraje = new RepoTraje(session);
        RepoEvento repoEvento = new RepoEvento(session);

        int opcion;
        do {
            Menus.menu1();
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1:{
                    int id = EntradaDatos.leerInt("id del personaje");
                    String nombre = EntradaDatos.leerString("nombre del personaje");
                    String alias = EntradaDatos.leerString("alias del personaje");

                    String respuesta = EntradaDatos.leerString("QUIRES ASOCIARLE SU TRAJE ¿SI O NO?");
                    if(respuesta.equalsIgnoreCase("si")){

                        repoTraje.listarTrajes();
                        int idTraje = EntradaDatos.leerInt("ID DEL TRAJE");
                        Traje asociado = repoTraje.selectTraje(idTraje);

                        if(asociado.getPersonaje() != null){
                            System.out.println("TRAJE YA ASIGNADO A: "+asociado.getPersonaje().getNombre());
                            break;
                        }
                        Personaje nuevo = new Personaje(id,nombre,alias,asociado);
                        repoPersonaje.crearPersonaje(nuevo);

                    }else if(respuesta.equalsIgnoreCase("no")){
                        Personaje nuevo = new Personaje(id,nombre,alias,null);
                        repoPersonaje.crearPersonaje(nuevo);
                    }else{
                        System.out.println("RESPUESTA INVALIDA, SOLO SI O NO");
                    }
                    break;
                }
                case 2:{
                    repoPersonaje.listarPersonajes();
                    int id = EntradaDatos.leerInt("id del personaje a eliminar");

                    repoPersonaje.eliminarPersonaje(id);
                    break;
                }
                case 3:{
                    repoPersonaje.listarPersonajes();
                    int idPersonaje = EntradaDatos.leerInt("id del personaje el cual se va a modificar");
                    String alias = EntradaDatos.leerString("nuevo alias");

                    repoTraje.listarTrajes();
                    int idTraje = EntradaDatos.leerInt("id del traje");

                    repoPersonaje.modificarPersonaje(idPersonaje,alias,idTraje);
                    break;
                }
                case 4:{
                    repoHabilidad.listarHabilidades();
                    int id = EntradaDatos.leerInt("id de habilidad");
                    String nombre = EntradaDatos.leerString("nombre de habilidad");
                    String descripcion = EntradaDatos.leerString("descripcion de habilidad");

                    repoHabilidad.agregarHabilidad(id,nombre,descripcion);
                    break;
                }
                case 5:{
                    repoHabilidad.listarHabilidades();
                    String nombre = EntradaDatos.leerString("nombre de habilidad tal cual");

                    repoHabilidad.eliminarHabilidad(nombre);
                    break;
                }
                case 6:{
                    repoHabilidad.listarHabilidades();
                    int id = EntradaDatos.leerInt("id de habilidad a modificar");
                    String nombre = EntradaDatos.leerString("nuevo nombre");
                    String descripcion = EntradaDatos.leerString("nueva descripcion");

                    repoHabilidad.modificarHabilidad(id,nombre,descripcion);
                    break;
                }
                case 7:{
                    repoPersonaje.listarPersonajes();
                    String nombrePersonaje = EntradaDatos.leerString("nombre del personaje tal cual");

                    repoHabilidad.listarHabilidades();
                    String nombreHabilidad = EntradaDatos.leerString("nombre de la habilidad tal cual");

                    repoHabilidad.asignarHabilidad(nombrePersonaje,nombreHabilidad);
                    break;
                }
                case 8:{


                    break;
                }
                case 9:{

                    break;
                }
                case 10:{
                    repoPersonaje.listarPersonajes();
                    int id = EntradaDatos.leerInt("id del personaje");

                    Personaje personaje = (Personaje) session.createQuery("FROM Personaje p WHERE p.id =:idPersonaje")
                            .setParameter("idPersonaje",id)
                            .uniqueResult();
                    System.out.println(personaje);
                    break;
                }
                case 11:{
                    repoEvento.listarEvento();
                    String nombreEvento = EntradaDatos.leerString("nombre del evento tal cual");

                    repoEvento.filtrarPorEvento(nombreEvento);
                    break;
                }
                case 12:{
                    repoHabilidad.listarHabilidades();
                    String nombreHabildiad = EntradaDatos.leerString("nombre de habilidad tal cual");

                    repoHabilidad.filtrarHabilidad(nombreHabildiad);
                    break;
                }
                case 13:{
                    System.out.println("╔"+"═".repeat(18)+"╗"+"\n║ ADIOS........... ║\n╚"+"═".repeat(18)+"╝");
                    break;
                }
                default:{
                    System.out.println("╔"+"═".repeat(17)+"╗"+"\n║ OPCION INVALIDA ║\n╚"+"═".repeat(17)+"╝");
                    break;
                }
            }
        }while (opcion !=13);

        session.close();
    }
}