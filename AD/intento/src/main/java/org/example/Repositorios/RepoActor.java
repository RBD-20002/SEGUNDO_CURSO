package org.example.Repositorios;

import org.example.Entidades.Actores;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class RepoActor {

    private Session session;

    public RepoActor(Session session) {
        this.session = session;
    }

    public void crearActor(String nombre, LocalDate fecha, String nacionalidad){
        Transaction trans = session.beginTransaction();
        try{
            Actores nuevo = new Actores(nombre,fecha,nacionalidad);
            session.persist(nuevo);
            trans.commit();
            System.out.println("SE CREO ACTOR");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void eliminarActor(int id){
        Transaction trans = session.beginTransaction();
        try{
            Actores existe = (Actores) session.createQuery("FROM Actores a WHERE a.id_actor =:id")
                    .setParameter("id",id)
                    .uniqueResult();
            if(existe != null){
                session.remove(existe);
                trans.commit();
                System.out.println("SE ELIMINO ACTOR");
            }else{
                System.out.println("NO SE ENCONTRO ACTOR CON ESA ID");
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void listarActoresUSA(String nacionalidad){
        Transaction trans = session.beginTransaction();
        try{
            List<Actores> actores = (List<Actores>) session.createQuery("FROM Actores a WHERE a.nacionalidad =:nacion ORDER BY a.fecha_nacimiento DESC")
                    .setParameter("nacion",nacionalidad)
                    .getResultList();
            if(actores == null){
                System.out.println("NO HAY ACTORES CON NACIONALIDAD");
                trans.rollback();
                return;
            }

            for(Actores actor : actores){
                System.out.println("NOMBRE: "+actor.getNombre());
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
