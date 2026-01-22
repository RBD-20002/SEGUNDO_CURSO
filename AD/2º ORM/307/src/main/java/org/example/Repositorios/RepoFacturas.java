package org.example.Repositorios;

import org.example.Entidades.Cliente;
import org.example.Entidades.Factura;
import org.example.Entidades.Telefono;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class RepoFacturas {

    private Session session;

    public RepoFacturas(Session session) {
        this.session = session;
    }

    public void listarFacturas() {
        try {
            List<Factura> facturas = (List<Factura>) session.createQuery("FROM Factura f").getResultList();
            if (facturas.isEmpty()) {
                System.out.println("NO HAY FACTURAS EN LA BD");
                return;
            } else {
                System.out.println("FACTURAS ALMACENADAS:");
                for (Factura factura : facturas) {
                    System.out.println("ID: " + factura.getIdFactura() +
                            "\nCLIENTE: " +
                            "\n   -NOMBRE: " + factura.getCliente().getNombre() +
                            "\n   -DIRECCION: " + factura.getCliente().getDireccion() +
                            "\n   -TELEFONOS: " + factura.getCliente().getTelefonos() +
                            "\nDESCRIPCION: " + factura.getDescripcion() +
                            "\nPRECIO: " + factura.getPrecio());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarFactura(String descripcion, double precio, int id) {
        Transaction trans = session.beginTransaction();
        try {
            Cliente cliente = session.createQuery("FROM Cliente c WHERE c.idCliente =:id", Cliente.class).setParameter("id", id).uniqueResult();
            if (cliente != null) {

                Factura factura = new Factura(cliente, descripcion, precio, new Date());
                session.persist(factura);
                trans.commit();
                System.out.println("FACTURA AGREGADA");

            } else {
                System.out.println("NO SE PUEDE CREAR LA FACTURA PORQUE LA PERSONA ESCOGIDA NO EXISTE");
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void modificarFactura(String descripcion, double preico, int id) {
        Transaction trans = session.beginTransaction();
        try {
            int filaAfectada = session.createQuery("UPDATE Factura f SET f.descripcion =:des, f.precio =:pre WHERE f.idFactura =:id").setParameter("des", descripcion).setParameter("pre", preico).setParameter("id", id).executeUpdate();

            if (filaAfectada > 0) {
                System.out.println("FACTURA MODIFICADA CORRECTAMENTE");
                trans.commit();
            } else {
                System.out.println("SE ENCONTRO FACTURA PERO FALLO CON ALGUN DATO");
                trans.rollback();
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void eliminarFactura(int id) {
        Transaction trans = session.beginTransaction();
        try {
            Factura factura = (Factura) session.createQuery("FROM Factura f WHERE f.idFactura =:id").setParameter("id", id).uniqueResult();
            if (factura != null) {
                session.remove(factura);
                trans.commit();
                System.out.println("FACTURA CON ID " + id + " ELIMINADA");
            } else {
                System.out.println("NO HAY FACTURA CON ESA ID");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
