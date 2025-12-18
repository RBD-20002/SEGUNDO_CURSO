package org.example.Repositorios;

import org.example.Entidades.Habilidad;
import org.example.Entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoHabilidad {

    private Session session;

    public RepoHabilidad(Session session){
        this.session = session;
    }

    /*----------------------------VER INFORMACION----------------------------*/

    public void listarHabilidades(){
        try {
            List<Habilidad> habilidades = (List<Habilidad>) session.createQuery("FROM Habilidad p").getResultList();
            if(!habilidades.isEmpty()){
                System.out.println("HABILIDADES:");
                for(Habilidad habilidad : habilidades){
                    System.out.println(habilidad.toString());
                }
            }else{
                System.out.println("NO HAY HABILIDADES PARA MOSTRAR");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*----------------------------METODOS----------------------------*/

    public void agregarHabilidad(int id, String nombre, String descricpcion){
        Transaction trans = session.beginTransaction();
        try{
            Habilidad existe = (Habilidad) session.createQuery("FROM Habilidad h WHERE h.id =:idHabilidad")
                    .setParameter("idHabilidad",id)
                    .uniqueResult();
            if(existe == null){
                Habilidad habilidad = new Habilidad(id,nombre,descricpcion);

                session.persist(habilidad);
                trans.commit();
                System.out.println("SE AGREGO CORRECTAMENTE LA HABILIDAD");
            }else{
                System.out.println("LA ID INTRODUCIDA YA EXISTE");
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void eliminarHabilidad(String nombre){
        Transaction trans = session.beginTransaction();
        try{
            Habilidad existe = (Habilidad) session.createQuery("FROM Habilidad h WHERE h.nombre =:nombreHab")
                    .setParameter("nombreHab",nombre);
            if(existe != null){
                session.remove(existe);
                System.out.println("SE ELIMINO CORRECTAMENTE HABILIDAD");
                trans.commit();
            }else{
                System.out.println("NO SE ENCONTRO LA HABILIDAD QUE QUIERES ELIMINAR");
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void modificarHabilidad(int id, String nombre, String descripcion){
        Transaction trans = session.beginTransaction();
        try{
            Habilidad habilidad = (Habilidad) session.createQuery("FROM Habilidad h WHERE h.id =:idHab").setParameter("idHab",id).uniqueResult();
            if(habilidad != null){
                int filaAfectada = session.createQuery("UPDATE Habilidad h SET h.nombre =:nombre, h.descripcion =:descrip WHERE h.id =:id")
                        .setParameter("nombre",nombre)
                        .setParameter("descrip",descripcion)
                        .setParameter("id",id)
                        .executeUpdate();
                if(filaAfectada > 0) trans.commit();
                else trans.rollback();
            }else{
                System.out.println("NO SE ENCONTRO LA ID DE HABILIDAD QUE SE VA A MODIFICAR");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void asignarHabilidad(String nombrePersonaje, String nombreHabilidad){
        Transaction trans = session.beginTransaction();
        try{
            Personaje personaje = (Personaje) session.createQuery("FROM Personaje p WHERE p.nombre =:nombre")
                    .setParameter("nombre",nombrePersonaje)
                    .uniqueResult();
            if(personaje != null){
                Habilidad habilidad = (Habilidad) session.createQuery("FROM Habilidad h WHERE h.nombre =:nombre")
                        .setParameter("nombre",nombreHabilidad)
                        .uniqueResult();
                        if(habilidad != null){
                            if(!personaje.getHabilidad().contains(habilidad)){

                                personaje.getHabilidad().add(habilidad);
                                session.merge(personaje);
                                trans.commit();
                                System.out.println("SE ASIGNO CORRECTAMENTE LA HABILIDAD DE "+nombreHabilidad+" AL PERSONAJE "+nombrePersonaje);
                            }else{
                                System.out.println("EL PERSONAJE YA CUENTA CON ESA HABILIDAD");
                                trans.rollback();
                            }
                        }else{
                            System.out.println("LA HABILIDAD INTRODUCIDA NO EXISTE");
                            trans.rollback();
                        }
            }else{
                System.out.println("EL PERSONAJE INTRODUCIDO NO EXISTE");
                trans.rollback();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void filtrarHabilidad(String nombreHabilidad){
        try{
            Long contador = (Long) session.createNativeQuery("SELECT COUNT(id_habilidad) FROM PERSONAJE_HABILIDAD ph JOIN HABILIDAD h ON ph.id_habilidad = h.id WHERE h.nombre =:nombre")
                    .setParameter("nombre",nombreHabilidad)
                    .uniqueResult();
            if(contador > 0){
                System.out.println("HAY "+contador+" CON LA HABILIDAD "+nombreHabilidad.toUpperCase());
            }else{
                System.out.println("NO HAY PERSONAJES QUE TENGAN LA HABILIDAD "+nombreHabilidad.toUpperCase());
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
