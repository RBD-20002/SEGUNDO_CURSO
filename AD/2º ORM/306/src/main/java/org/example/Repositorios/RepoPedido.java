package org.example.Repositorios;

import org.example.Entidades.LineaPedido;
import org.example.Entidades.Pedido;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoPedido {

    private Session session;

    public RepoPedido(Session session){
        this.session = session;
    }

    public void mostrarPedidos(){
        try{
            List<Pedido> pedidos = (List<Pedido>) session.createQuery("FROM Pedido p").getResultList();
            if(!pedidos.isEmpty()){
                System.out.println("PEDIDOS:");
                for(Pedido pedido : pedidos){
                    System.out.println(pedido.toString());
                }
            }else{
                System.out.println("NO HAY PEDIDOS GUARDADOS");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void pedidoPorCliente(int id){
        try{
            String sql = "SELECT lp.* FROM producto p JOIN lineapedido lp ON p.idProducto = lp.idProducto JOIN pedido pe ON lp.idPedido = pe.idPedido JOIN cliente cl ON pe.idCliente = cl.idCliente WHERE cl.idCliente =:id";
            List<LineaPedido> lineas = session.createNativeQuery(sql, LineaPedido.class).setParameter("id",id).getResultList();
            if(!lineas.isEmpty()){
                System.out.println("PEDIDOS:");
                for(LineaPedido linea : lineas){
                    System.out.println(linea.toString());
                }
            }else{
                System.out.println("NO HAY PEDIDO QUE MOSTRAR");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void eliminarPedido(int id){
        Transaction trans = session.beginTransaction();
        try{
            Pedido pedido = session.createQuery("FROM Pedido p WHERE p.idPedido =:id", Pedido.class).setParameter("id",id).uniqueResult();

            if(pedido == null){
                System.out.println("NO HAY PEDIDO CON ESA ID");
                return;
            }else{
                session.remove(pedido);
                System.out.println("PEDIOD ELIMINADO");
                trans.commit();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
