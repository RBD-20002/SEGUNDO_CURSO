package org.example.Repositorios;

import org.example.Entidades.Personaje;
import org.example.Entidades.Traje;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class RepoTraje {

    private Session session;

    public RepoTraje(Session session){
        this.session = session;
    }

    /*----------------------------VER INFORMACION----------------------------*/

    public void listarTrajes(){
        try {
            List<Traje> trajes = (List<Traje>) session.createQuery("FROM Traje j").getResultList();
            if(!trajes.isEmpty()){
                for(Traje traje : trajes){
                    System.out.println(traje.toString());
                }
            }else{
                System.out.println("NO HAY TRAJES");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*----------------------------METODOS----------------------------*/

    public void agregarTraje(Traje traje){
        Transaction trans = session.beginTransaction();
        try{
            Traje existe = (Traje) session.createQuery("FROM Traje t WHERE t.id =:idTraje").setParameter("idTraje",traje.getId()).uniqueResult();
            if(existe == null){
                session.persist(traje);
                trans.commit();
            }else{
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void cambiarTraje(String nombrePersonaje, String especificacionTraje) {
        Transaction trans = session.beginTransaction();
        try {
            Personaje personaje = (Personaje) session.createQuery("FROM Personaje p WHERE p.nombre =:nombre")
                    .setParameter("nombre", nombrePersonaje)
                    .uniqueResult();

            if (personaje == null) {
                System.out.println("EL PERSONAJE INTRODUCIDO NO EXISTE");
                trans.rollback();
                return;
            }

            Traje traje = (Traje) session.createQuery("FROM Traje t WHERE t.especificacion =:especificacion")
                    .setParameter("especificacion", especificacionTraje)
                    .uniqueResult();

            if (traje == null) {
                System.out.println("EL TRAJE INTRODUCIDO NO EXISTE");
                trans.rollback();
                return;
            }

            personaje.setTraje(traje);
            session.merge(personaje);
            trans.commit();

            System.out.println("EL TRAJE DEL PERSONAJE "+nombrePersonaje+" SE CAMBIO CORRECTAMENTE");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }


    /*----------------------------METODOS EXTRAS----------------------------*/

    public boolean trajeDisponible(int id){
        Transaction trans = session.beginTransaction();
        try{
            Traje traje = (Traje) session.createQuery("FROM Traje t WHERE t.id =:id")
                    .setParameter("id",id)
                    .uniqueResult();
            if(traje == null){
                System.out.println("NO EXISTE TRAJE CON ID "+id);
                return false;
            }
            if(traje.getPersonaje() != null){
                System.out.println("TRAJE ASIGNADO A "+traje.getPersonaje().getNombre());
                return false;
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Traje selectTraje(int id){
        try{
            Traje traje = (Traje) session.createQuery("FROM Traje t WHERE t.id =:idTraje")
                    .setParameter("idTraje",id)
                    .uniqueResult();
            if(traje == null){
                System.out.println("NO EXISTE TRAJE CON ID "+id);
                return null;
            }
            if(traje.getPersonaje() != null){
                System.out.println("EL TRAJA YA LO ESTA USANDO "+traje.getPersonaje().getNombre());
                return null;
            }
            return traje;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
