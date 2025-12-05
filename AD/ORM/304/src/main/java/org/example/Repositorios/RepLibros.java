package org.example.Repositorios;

import jakarta.persistence.Query;
import org.example.Entidades.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepLibros {

    private Session session;

    public RepLibros(Session session){
        this.session = session;
    }

    public void addBook(Libros libro){
        Transaction trans = session.beginTransaction();
        try{
            session.persist(libro);
            trans.commit();

            System.out.println("SE AGREGO LIBRO: "+libro.getTitulo());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void delBook(Libros libro){
        Transaction trans = session.beginTransaction();
        try{
            session.remove(libro);
            trans.commit();

            System.out.println("SE ELIMINO LIBRO: "+libro.getTitulo());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public Libros selectForTitle(String titulo){
        Transaction trans = session.beginTransaction();
        try{
            Libros libro = (Libros) session.createQuery("FROM Libros lb WHERE titulo =:title").setParameter("title",titulo).getSingleResult();

            System.out.println("LIBRO CON TITULO "+titulo+":\nID: "+libro.getId_libro()+"\nTITULO: "+libro.getTitulo()+"\nPRECIO: "+libro.getPrecio());
            trans.commit();
            return libro;
         }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
            return null;
        }
    }

    public void selectForAuthor(String autor){
        Transaction trans = session.beginTransaction();
        try{
            List<Libros> libros = session.createQuery("FROM Libros lb WHERE lb.autores.nombre =:autor").setParameter("autor",autor).getResultList();

            for (Libros libro : libros){
                System.out.println("-".repeat(20));
                System.out.println(libro.toString());
            }
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void selectAllBooks(){
        Transaction trans = session.beginTransaction();
        try{
            List<Libros> libros = session.createQuery("FROM Libros lb").getResultList();

            for(Libros libro : libros){
                System.out.println("-".repeat(20));
                System.out.println(libro.toString());
            }

            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void actualizar(Libros libro){
        Transaction trans = session.beginTransaction();
        session.update(libro);
        trans.commit();
    }
}
