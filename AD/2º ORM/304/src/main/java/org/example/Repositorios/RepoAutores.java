package org.example.Repositorios;

import org.example.Entidades.Autores;
import org.example.Entidades.Libros;
import org.example.Entidades.Telefono;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoAutores {

    private Session session;

    public RepoAutores(Session session){
        this.session = session;
    }

    public void insertAutor(Autores autor){
        Transaction trans = session.beginTransaction();
        try{
            session.persist(autor);
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void deleteAutor(String dni){
        Transaction trans = session.beginTransaction();
        try{
            Autores autor = (Autores) session.createQuery("FROM Autores a WHERE DNI ="+dni).uniqueResult();
            session.remove(autor);
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void insertarTelefono(String dniAut, String numero){
        Transaction trans = session.beginTransaction();
        try{
            Autores autor = (Autores) session.createQuery("FROM Autores a WHERE a.dni=:dniAut").setParameter("dniAut",dniAut).uniqueResult();
            if(autor != null){
                Telefono telefono = new Telefono(dniAut, numero, autor);
                session.persist(telefono);
                trans.commit();
            }else{
                System.out.println("AUTOR NO ENCONTRADO");
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

   public void verLibrosDeAutor(String dni){
        Transaction trans = session.beginTransaction();
        try{
            Autores autor = (Autores) session.createQuery("FROM Autores a WHERE a.dni=:dniAutor").setParameter("dniAutor",dni).uniqueResult();
            if(autor != null){
                System.out.println("LIBROS DE "+autor.getNombre()+":");
                for(Libros libro : autor.getLibros()){
                    System.out.println(libro.getTitulo()+" -> "+libro.getPrecio());
                }
            }else{
                System.out.println("NOSE ENCONTRO AUTOR CON DNI "+dni);
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
   }

   public void verAutorLibro(){
        try {
            List<Autores> autores = session.createQuery("FROM Autores").getResultList();

            for(Autores autor : autores){
                System.out.println("AUTOR: "+autor.getNombre());
                if(autor.getLibros().isEmpty()){
                    System.out.println("EL AUTOR AUN NO TIENE LIBROS");
                }else{
                    for(Libros libro : autor.getLibros()){
                        System.out.println("TITULO: "+libro.getTitulo());
                    }
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
   }

   public void selectForDni(String dni){
        try{
            Autores autor = (Autores) session.createQuery("FROM Autores a WHERE a.dni=:dniAutor").setParameter("dniAutor",dni).getSingleResult();
            if(autor != null){
                System.out.println("AUTOR: "+autor.getNombre()+" DNI: "+autor.getDni());
            }else{
                System.out.println("NO HAY AUTOR CON DNI "+dni);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
   }

    public void enlazarAutorLibro(String dniAutor, int idLibro) {
        Transaction trans = session.beginTransaction();
        try {
            Autores autor = session.get(Autores.class, dniAutor);
            Libros libro = session.get(Libros.class, idLibro);

            if (autor != null && libro != null) {
                autor.getLibros().add(libro);
                libro.getAutores().add(autor);
                trans.commit();
                System.out.println("🔗 " + autor.getNombre() + " enlazado con " + libro.getTitulo());
            } else {
                System.out.println("⚠️ Autor o libro no encontrado");
            }
        } catch (Exception e) {
            trans.rollback();
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
