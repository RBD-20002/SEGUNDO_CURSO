package org.example.Repositorios;

import org.example.Entidades.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepoCliente {

    private Session session;

    public RepoCliente(Session session){
        this.session = session;
    }

    public Cliente obtenerCliente(int idCliente){
        try {
            Cliente cliente = (Cliente) session.createQuery("FROM Cliente c WHERE c.idCliente=:id").setParameter("id",idCliente).getSingleResult();
            if(cliente != null){
                return cliente;
            }else{
                System.out.println("NO SE ENCONTRO CLIENTE CON LA ID "+idCliente);
                return null;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void agregarCliente(Cliente cliente){
        Transaction trans = session.beginTransaction();
        try{
            session.persist(cliente);
            trans.commit();
            System.out.println("SE AGREGO CLIENTE");
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void modificarCliente(Cliente cliente){
        Transaction trans = session.beginTransaction();
        try{
            session.update(cliente);
            trans.commit();
            System.out.println("CLIENTE MODIFICADO");
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void borrarCliente(Cliente cliente){
        Transaction trans = session.beginTransaction();
        try{
            session.remove(cliente);
            trans.commit();
            System.out.println("SE BORRO EL CLIENTE "+cliente.getNombre());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
