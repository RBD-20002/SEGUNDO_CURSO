package org.example.Repositorios;

import org.example.Entidades.Premios;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class RepoPremio {
    private Session session;

    public RepoPremio(Session session) {
        this.session = session;
    }

    public void crearPremio(String nombre, int fecha){
        Transaction trans = session.beginTransaction();
        try{
            Premios nuevo = new Premios(nombre,fecha);

            session.persist(nuevo);
            trans.commit();
            System.out.println("SE CREO PREMIO");
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void eliminarPremio(int id){
        Transaction trans = session.beginTransaction();
        try{
            Premios existe = (Premios) session.createQuery("FROM Premios p WHERE p.id_premio =:id")
                    .setParameter("id",id)
                    .uniqueResult();
            if(existe != null){
                session.remove(existe);
                trans.commit();
                System.out.println("SE ELIMINO PREMIO");
            }else{
                System.out.println("NO SE ENCONTRO PREMIO A ELIMNAR");
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
