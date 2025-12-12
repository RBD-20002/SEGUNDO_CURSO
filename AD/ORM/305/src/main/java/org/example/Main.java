package org.example;

import org.example.Entidades.Cliente;
import org.example.Repositorios.RepoAlquiler;
import org.example.Repositorios.RepoCliente;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();
        RepoAlquiler repoAlquiler = new RepoAlquiler(session);
        RepoCliente repoCliente = new RepoCliente(session);

        Cliente cliente = repoCliente.obtenerCliente(2);
        System.out.println(cliente.toString());
        System.out.println("PASO 1");

        Cliente nuevoCliente = new Cliente("123456", "Cliente1", "Cliente1@cliente.es");
        repoCliente.agregarCliente(nuevoCliente);
        session.refresh(nuevoCliente);
        System.out.println(nuevoCliente);
        System.out.println("PASO 2");

        nuevoCliente.setDNI("789456");
        repoCliente.modificarCliente(nuevoCliente);
        System.out.println(nuevoCliente);
        System.out.println("PASO 3");

        repoCliente.borrarCliente(nuevoCliente);
        System.out.println("PASO 4");

        // Ya está prestado daría error
        repoAlquiler.alquilar(3, 1);
        System.out.println("PASO 5");

        repoAlquiler.devolver(3);
        System.out.println("PASO 6");

        // No daría error porque ahora ya se ha devuelto
        repoAlquiler.alquilar(3, 1);
        System.out.println("PASO 7");
        session.close();
    }
}