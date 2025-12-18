package org.example.Repositorios;

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

    public Traje selectTraje(int id){
        try{
            Traje traje = (Traje) session.createQuery("FROM Traje t WHERE t.id =:idTraje")
                    .setParameter("idTraje",id)
                    .uniqueResult();
            if(traje != null) return traje;
            else {
                System.out.println("NO HAY TRAJE CON ESE ID");
                return null;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
