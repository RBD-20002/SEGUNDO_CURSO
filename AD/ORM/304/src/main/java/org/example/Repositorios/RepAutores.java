package org.example.Repositorios;

import org.example.Entidades.Autores;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepAutores {

    private static Session session;

    public RepAutores(Session session){
        this.session = session;
    }

    public void addAuthor(Autores autor){
        Transaction trans = session.beginTransaction();
        try{
            session.persist(autor);
            trans.commit();

            System.out.println("SE AGREGO AUTOR: "+autor.getNombre());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void delAuthor(Autores autor){
        Transaction trans = session.beginTransaction();
        try{
            session.remove(autor);
            trans.commit();

            System.out.println("SE ELIMINO AUTOR: "+autor.getNombre());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void selectAllAuthor(){
        Transaction trans = session.beginTransaction();
        try{
            List<Autores> autores = session.createQuery("FROM Autores au").getResultList();

            for(Autores autor : autores){
                System.out.println("-".repeat(30));
                System.out.println(autor.toString());
            }
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public Autores selectForDNI(String dni){
        Transaction trans = session.beginTransaction();
        try{
            Autores autor = (Autores) session.createQuery("FROM Autores au WHERE DNI_autor =:dni").setParameter("dni",dni).getSingleResult();
            System.out.println(autor.toString());

            trans.commit();
            return autor;
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
            return null;
        }
    }

    public void actualizar(Autores autor){
        Transaction trans = session.beginTransaction();
        session.update(autor);
        trans.commit();
    }
}
