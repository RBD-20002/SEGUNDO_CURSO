package org.example;

import org.example.Repositorios.RepoActor;
import org.example.Repositorios.RepoPelicula;
import org.example.Repositorios.RepoPremio;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();
        RepoActor repoActor = new RepoActor(session);
        RepoPelicula repoPelicula = new RepoPelicula(session);
        RepoPremio repoPremio = new RepoPremio(session);

        int opcion;
        do{
            System.out.println("1. Crear actor\n" +
                    "2. Eliminar actor\n" +
                    "3. Crear pelicula\n" +
                    "4. Eliminar pelicula\n" +
                    "5. Crear premio\n" +
                    "6. Eliminar premio\n" +
                    "7. Modificar género de una película\n" +
                    "8. Asignar un premio a una película\n" +
                    "9. Asignar un actor a una película\n" +
                    "10. Asignar una película a una sala en una fecha y una hora\n" +
                    "11. Consulta 1\n" +
                    "12. Consulta 2\n" +
                    "13. Consulta 3\n" +
                    "14. Consulta 4\n" +
                    "15. Salir");
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String nombre = ED.leerString("nombre");
                    LocalDate fecha = ED.leerDate("fecha");
                    String nacionalidad = ED.leerString("nacionalidad");

                    repoActor.crearActor(nombre,fecha,nacionalidad);
                    break;
                }
                case 2:{
                    int id = ED.leerInt("id");

                    repoActor.eliminarActor(id);
                    break;
                }
                case 3:{
                    String titulo = ED.leerString("titulo");
                    int fecha = ED.leerInt("año");
                    String genero = ED.leerString("genero");

                    repoPelicula.crearPelicula(titulo,fecha,genero);
                    break;
                }
                case 4:{
                    int id = ED.leerInt("id");

                    repoPelicula.eliminarPelicula(id);
                    break;
                }
                case 5:{
                    String nombre = ED.leerString("nombre");
                    int fecha = ED.leerInt("fecha");

                    repoPremio.crearPremio(nombre,fecha);
                    break;
                }
                case 6:{
                    int id = ED.leerInt("id");

                    repoPremio.eliminarPremio(id);
                    break;
                }
                case 7:{
                    int id = ED.leerInt("id");
                    String genero = ED.leerString("genero");

                    repoPelicula.modificarGenero(id,genero);
                    break;
                }
                case 8:{
                    int idPremio = ED.leerInt("id premio");
                    int idPelicula = ED.leerInt("id pelicula");

                    repoPelicula.asignarPremio(idPremio, idPelicula);
                    break;
                }
                case 9:{
                    int idPelicula = ED.leerInt("id pelicula");
                    int idActor = ED.leerInt("id actor");

                    repoPelicula.asignarActor(idActor, idPelicula);
                    break;
                }
                case 10:{
                    System.out.println("NO SE HIZO");
                    break;
                }
                case 11:{
                    String nacionalidad = ED.leerString("nacionalidad");

                    repoActor.listarActoresUSA(nacionalidad);
                    break;
                }
                case 12:{
                    System.out.println("NO SE HIZO");
                    break;
                }
                case 13:{
                    LocalDate fecha = ED.leerDate("fecha");

                    repoPelicula.listarPeliPorFecha(fecha);
                    break;
                }
                case 14:{
                    System.out.println("NO SE HIZO");
                    break;
                }
                case 15:{
                    System.out.println("ADIOS");
                    break;
                }
                default:{
                    System.out.println("INVALIDA");
                    break;
                }
            }
        }while (opcion != 15);

        session.close();
    }
}