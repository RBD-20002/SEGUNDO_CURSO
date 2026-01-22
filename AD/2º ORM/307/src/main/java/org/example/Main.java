package org.example;

import org.example.Repositorios.RepoFacturas;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();
        RepoFacturas repoFacturas = new RepoFacturas(session);

        System.out.println("PARTE 1");
        repoFacturas.listarFacturas();
        System.out.println("PASO 1");

        System.out.println("-".repeat(50));

        System.out.println("PARTE 2");
        repoFacturas.agregarFactura("Prueba 2",1300.0,2);
        System.out.println("PASO 2");

        System.out.println("-".repeat(50));

        System.out.println("PARTE 3");
        repoFacturas.modificarFactura("MODIFICACION DE PRUEBA",100.0,8);
        System.out.println("PASO 3");

        System.out.println("-".repeat(50));

        System.out.println("PARTE 4");
        repoFacturas.eliminarFactura(7);
        System.out.println("PASO 4");

        System.out.println("-".repeat(50));
        session.close();
    }
}