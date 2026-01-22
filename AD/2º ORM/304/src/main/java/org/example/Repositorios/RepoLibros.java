package org.example.Repositorios;

import org.example.Entidades.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoLibros {

    private Session session;

    public RepoLibros(Session session){
        this.session = session;
    }

    public void insertLibro(Libros libro){
        Transaction trans = session.beginTransaction();
        try{
            session.persist(libro);
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void deleteLibro(int libro){
        Transaction trans = session.beginTransaction();
        try{
            session.remove(libro);
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void filtrarTitulo(String title){
        try{
            Libros libro = (Libros) session.createQuery("FROM Libros l WHERE l.titulo=:title").setParameter("title",title).getSingleResult();
            if(libro != null){
                System.out.println("LIBRO ENCONTRADO:\n"+libro.toString());
            }else{
                System.out.println();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void listarLibros(){
        try{
            List<Libros> libros = session.createQuery("FROM Libros l").getResultList();
            if(!libros.isEmpty()){
               for(Libros libro : libros){
                   System.out.println(libro.toString()+"\n");
               }
            }else{
                System.out.println("NO HAY LIBROS QUE MOSTRAR");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
