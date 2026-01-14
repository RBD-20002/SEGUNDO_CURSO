package org.example.Repositorios;

import org.example.Entidades.Actores;
import org.example.Entidades.Peliculas;
import org.example.Entidades.Premios;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class RepoPelicula {
    private Session session;

    public RepoPelicula(Session session) {
        this.session = session;
    }

    public void crearPelicula(String titulo, int fecha, String genero) {
        Transaction trans = session.beginTransaction();
        try {
            Peliculas nuevo = new Peliculas(titulo, fecha, genero);
            session.persist(nuevo);
            trans.commit();
            System.out.println("SE CREO PELICULA");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void eliminarPelicula(int id) {
        Transaction trans = session.beginTransaction();
        try {
            Peliculas pelicula = session.get(Peliculas.class, id);
            if (pelicula != null) {
                session.remove(pelicula);
                trans.commit();
                System.out.println("SE ELIMINO PELICULA");
            } else {
                System.out.println("NO SE ENCONTRO PELICULA CON ESA ID ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void modificarGenero(int id, String genero) {
        Transaction trans = session.beginTransaction();
        try {
            Peliculas peliculas = (Peliculas) session.get(Peliculas.class, id);
            if (peliculas != null) {
                peliculas.setGenero(genero);
                session.merge(peliculas);
                trans.commit();
                System.out.println("SE MODIFICO EL GENERO DE LA PELICULA");
            }else{
                System.out.println("NO SE ENCONTRO PELICULA CON ESA ID");
                trans.rollback();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void asignarActor(int idActor, int idPelicula){
        Transaction trans = session.beginTransaction();
        try{
            Actores actor = (Actores) session.get(Actores.class, idActor);
            if(actor == null){
                System.out.println("NO SE ENCONTRO ACTOR CON ID");
                trans.rollback();
                return;
            }

            Peliculas pelicula = (Peliculas) session.get(Peliculas.class, idPelicula);
            if(pelicula == null){
                System.out.println("NO SE ENCONTRO PELI CON ID");
                trans.rollback();
                return;
            }

            actor.getPeliculas().add(pelicula);
            pelicula.getActores().add(actor);

            trans.commit();
            System.out.println("SE ASIGNO ACTOR");
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void asignarPremio(int idPremio, int idPelicula){
        Transaction trans = session.beginTransaction();
        try{
            Premios premio = (Premios) session.get(Premios.class,idPremio);
            if(premio == null){
                System.out.println("NO SE ENCONTRO PREMIO CON ID");
                trans.rollback();
                return;
            }

            Peliculas peliculas = (Peliculas) session.get(Peliculas.class,idPelicula);
            if(peliculas == null){
                System.out.println("NO SE ENCONTRO PELI CON ID");
                trans.rollback();
                return;
            }

            peliculas.setPremios(premio);

            trans.commit();
            System.out.println("SE ASIGNO PREMIO");
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void listarPeliPorFecha(LocalDate fecha){
        Transaction trans = session.beginTransaction();
        try{
            List<Peliculas> peliculas = (List<Peliculas>) session.createQuery("SELECT p FROM Peliculas p JOIN Proyecciones pr ON p.id_pelicula = pr.pelicula_id WHERE pr.fecha =:fecha")
                    .setParameter("fecha",fecha)
                    .getResultList();
            if(peliculas == null){
                System.out.println("NO HAY PELICULAS EN ESA FECHA");
                trans.rollback();
                return;
            }

            for (Peliculas peli : peliculas){
                System.out.println("TITULO: "+peli.getTitulo()+" GENERO: "+peli.getGenero());
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
