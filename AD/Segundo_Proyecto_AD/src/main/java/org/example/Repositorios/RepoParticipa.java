package org.example.Repositorios;

import org.example.Entidades.Evento;
import org.example.Entidades.Participa;
import org.example.Entidades.ParticipaId;
import org.example.Entidades.Personaje;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;

public class RepoParticipa {

    private Session session;

    public RepoParticipa(Session session){
        this.session = session;
    }

    public void registrarParticipacion(String personaje, String evento, String rol, Date fecha) {
        Transaction trans = session.beginTransaction();
        try{
            Personaje idPersonaje = (Personaje) session.createQuery("FROM Personaje p WHERE p.nombre =:personaje")
                    .setParameter("personaje",personaje)
                    .uniqueResult();
            if(idPersonaje == null){
                System.out.println("NO SE ENCONTRO LA ID DEL PERSONAJE "+personaje);
                trans.rollback();
                return;
            }

            Evento idEvento = (Evento) session.createQuery("FROM Evento e WHERE e.nombre =:evento")
                    .setParameter("evento",evento)
                    .uniqueResult();
            if(idEvento == null){
                System.out.println("NO SE ENCONTRO LA ID DEL EVENTO "+evento);
                trans.rollback();
                return;
            }

            ParticipaId participacionId = new ParticipaId(idPersonaje.getId(), idEvento.getId());
            Participa participacionExistente = session.get(Participa.class, participacionId);

            if (participacionExistente != null) {
                System.out.println("LA PARTICIPACION YA EXISTE");
                trans.rollback();
                return;
            }

            Participa participacion = new Participa(idPersonaje, idEvento, fecha, rol);
            session.persist(participacion);
            trans.commit();
            System.out.println("LA PARTICIPACION SE REGISTRO CORRECTAMENTE");
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}