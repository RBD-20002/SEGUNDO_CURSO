package org.example.Repositorios;

import org.example.Entidades.Alquiler;
import org.example.Entidades.Cliente;
import org.example.Entidades.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Date;

public class RepoAlquiler {

    private Session session;

    public RepoAlquiler(Session session){
        this.session = session;
    }

    public boolean esAlquilado(int idLibro){
        Transaction trans = session.beginTransaction();
        try{
            Boolean alquilado = (Boolean) session.createQuery("SELECT a.alquilado FROM Alquiler a WHERE a.libro.idLibro =:id").setParameter("id",idLibro).uniqueResult();
            trans.commit();
            if(alquilado == null){
                return false;
            }
            return alquilado;
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
            return false;
        }
    }

    public void alquilar(int idLibro, int idCliente){
        Transaction trans = session.beginTransaction();
        try{
            Boolean alquilado = esAlquilado(idLibro);
            if(alquilado){
                System.out.println("EL LIBRO ESTA ALQUILADO");
                trans.rollback();
                return;
            }

            Cliente cliente = (Cliente) session.createQuery("FROM Cliente c WHERE c.idCliente =:id").setParameter("id",idCliente).getSingleResult();
            if(cliente == null){
                System.out.println("CLIENTE NO EXISTE");
                trans.rollback();
                return;
            }

            Libro libro = (Libro) session.createQuery("FROM Libro l WHERE l.idLibro=:id").setParameter("id",idLibro).uniqueResult();
            if(libro == null){
                System.out.println("LIBRO NO EXISTE");
                trans.rollback();
                return;
            }

            Alquiler nuevoAlquiler = new Alquiler(libro,cliente, LocalDate.now(),true);
            session.persist(nuevoAlquiler);
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void devolver(int idLibro){
        Transaction trans = session.beginTransaction();
        try{
            int filas =  session.createQuery("UPDATE Alquiler c SET c.alquilado = FALSE WHERE c.idLibro=:id").setParameter("id",idLibro).executeUpdate();
            if(filas == 0){
                System.out.println("NO SE ENCONTRO ESE LIBRO");
                trans.rollback();
                return;
            }

            System.out.println("SE DEVOLVIO CORRECTAMENTE EL LIBRO CON ID "+idLibro);
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
