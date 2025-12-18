package org.example.Repositorios;

import org.example.Entidades.Personaje;
import org.example.Entidades.Traje;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoPersonaje {

    private Session session;

    public RepoPersonaje(Session session){
        this.session = session;
    }

    /*----------------------------VER INFORMACION----------------------------*/

    public void listarPersonajes(){
        try {
            List<Personaje> personajes = (List<Personaje>) session.createQuery("FROM Personaje p").getResultList();
            if(!personajes.isEmpty()){
                System.out.println("PERSONAJES:");
                for(Personaje personaje : personajes){
                    System.out.println("| ID: "+personaje.getId()+" | NOMBRE: "+personaje.getNombre()+" |");
                }
            }else{
                System.out.println("NO HAY PERSONAJES PARA MOSTRAR");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*----------------------------METODOS----------------------------*/

    public void crearPersonaje(Personaje personaje){
        Transaction trans = session.beginTransaction();
        try{
            Personaje existe = (Personaje) session.createQuery("FROM Personaje p WHERE p.nombre =:nombre")
                    .setParameter("nombre",personaje.getNombre())
                    .uniqueResult();
            if(existe == null){
                session.persist(personaje);
                trans.commit();
                System.out.println("SE AGREGO NUEVO PERSONAJE "+personaje.getNombre());
            }System.out.println("YA EXISTE EL PERSONAJE "+personaje.getNombre());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void eliminarPersonaje(int id){
        Transaction trans = session.beginTransaction();
        try{
            Personaje existe = (Personaje) session.createQuery("FROM Personaje p WHERE p.id =:idPersonaje").setParameter("idPersonaje",id).uniqueResult();
            if(existe != null){
                session.remove(existe);
                trans.commit();
                System.out.println("SE ELIMINO PERSONAJE CORRECTAMENTE");
            }else{
                System.out.println("NO EXISTE EL PERSONAJE CON ID: "+id);
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void modificarPersonaje(int idPersonaje, String alias, int idTraje){
        Transaction trans = session.beginTransaction();
        try{
            Traje traje = (Traje) session.createQuery("FROM Traje t WHERE t.id =:idTraje")
                    .setParameter("idTraje",idTraje)
                    .uniqueResult();
            if(traje == null){
                System.out.println("NO EXISTE TRAJE CON ESA ID");
                trans.rollback();
                return;
            }

            Personaje personajeConTraje = (Personaje) session.createQuery("FROM Personaje p WHERE p.traje.id =:idTraje AND p.id !=:idPersonaje")
                    .setParameter("idTraje",idTraje)
                    .setParameter("idPersonaje",idPersonaje)
                    .uniqueResult();

            if(personajeConTraje != null){
                System.out.println("EL TRAJE CON ID "+idTraje+" LO TIENE EN USO EL PERSONAJE CON ID "+idPersonaje);
                trans.rollback();
                return;
            }

            int filaAfectada = session.createQuery("UPDATE Personaje p SET p.alias =:alias, p.traje.id =:idTraje WHERE p.id =:idPersonaje")
                    .setParameter("alias",alias)
                    .setParameter("idTraje",idTraje)
                    .setParameter("idPersonaje",idPersonaje)
                    .executeUpdate();
            if(filaAfectada > 0){
                System.out.println("SE MODIFICO DATOS DEL PERSONAJE CON ID: "+idPersonaje);
                trans.commit();
            }else{
                System.out.println("NO EXISTE PERSONAJE CON ID: "+idPersonaje);
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
