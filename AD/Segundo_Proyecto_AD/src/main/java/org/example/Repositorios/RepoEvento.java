package org.example.Repositorios;

import org.example.Entidades.Evento;
import org.example.Entidades.Personaje;
import org.hibernate.Session;
import java.util.List;

public class RepoEvento {

    private Session session;

    public RepoEvento(Session session){
        this.session = session;
    }

    /*----------------------------VER INFORMACION----------------------------*/

    public void listarEvento(){
        try {
            List<Evento> eventos = (List<Evento>) session.createQuery("FROM Evento")
                    .getResultList();
            if(eventos != null){
                for(Evento evento : eventos){
                    System.out.println(evento);
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*----------------------------METODOS----------------------------*/

    public void filtrarPorEvento(String nombreEvento){
        try {
            Evento evento = (Evento) session.createQuery("FROM Evento e WHERE e.nombre =:nombreEvento")
                    .setParameter("nombreEvento",nombreEvento)
                    .uniqueResult();
            if(evento != null){
                List<Personaje> personajes = (List<Personaje>) session.createQuery("SELECT pe FROM Evento e JOIN Participa pa ON e.id = pa.evento.id JOIN Personaje pe ON pa.personaje.id = pe.id WHERE e.nombre =:nombreEvento")
                        .setParameter("nombreEvento",nombreEvento)
                        .getResultList();
                if(personajes != null){
                    System.out.println("PARTICIPARON EN "+nombreEvento);
                    for(Personaje personaje : personajes){
                        System.out.println("║ NOMBRE: "+personaje.getNombre()+" ║");
                    }
                }else{
                    System.out.println("NO HAY REGISTRO QUE ALGUN PERSONAJE HAYA PARTICIPADO EN EL EVENTO "+nombreEvento);
                }
            }else{
                System.out.println("NO EXISTE EL EVENTO CON NOMBRE "+nombreEvento);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
