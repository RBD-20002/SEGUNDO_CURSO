package org.example.Repositorios;

import jakarta.persistence.Query;
import org.example.Entidades.Telefonos;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepTelefonos {

    private Session session;

    public RepTelefonos(Session session){
        this.session = session;
    }

    public void addTelefono(Telefonos telefono){
        Transaction trans = session.beginTransaction();
        try{
            session.persist(telefono);
            trans.commit();

            System.out.println("SE AGREGO TELEFONO A AUTOR: "+telefono.getAutor());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void delTelefono(Telefonos telefono){
        Transaction trans = session.beginTransaction();
        try{
            session.remove(telefono);
            trans.commit();

            System.out.println("SE ELIMINO EL TELEFONO "+telefono.getNumTelf()+" DEL AUTOR "+telefono.getAutor());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void selectAllTelf(){
        Transaction trans = session.beginTransaction();
        try{
            List<Telefonos> telefonos = session.createQuery("FROM Telefonos telf").getResultList();
            for(Telefonos telf : telefonos){
                System.out.println("-".repeat(40));
                System.out.println("AUTOR: "+telf.getAutor()+"\nTELEFONO: "+telf.getNumTelf());
            }
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void selectForAuthor(String nombre){
        Transaction trans = session.beginTransaction();
        try{
           Telefonos telefono = (Telefonos) session.createQuery("FROM Telefonos tlf WHERE tlf.autor.nombre =:nombre").setParameter("name",nombre).getSingleResult();

            System.out.println("TELEFONO DEL AUTOR: "+nombre+"\nTELEFONO: "+telefono.getNumTelf());
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void actualizar(Telefonos telefono){
        Transaction trans = session.beginTransaction();
        session.update(telefono);
        trans.commit();
    }
}
