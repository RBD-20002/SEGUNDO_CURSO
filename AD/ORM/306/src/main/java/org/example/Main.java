package org.example;

import org.example.Repositorios.RepoPedido;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();
        RepoPedido repoPedido = new RepoPedido(session);

        System.out.println("PRUEBA 1");
        repoPedido.mostrarPedidos();
        System.out.println("PASO \nFALTO LO SEGUNDO");

        System.out.println("PRUEBA 2");
        repoPedido.pedidoPorCliente(1);
        System.out.println("PASO \nFALTO LO TERCERO");

        System.out.println("PRUEBA 3");
        repoPedido.eliminarPedido(2);
        System.out.println("PASO");

        session.close();
        System.out.println("Finalizando la conexión a MySQL");
    }
}