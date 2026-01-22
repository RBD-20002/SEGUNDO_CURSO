package org.example.Repositorios;

import org.example.Entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepEmpleado implements Repositorio<Empleado> {

    private Session session;

    public RepEmpleado(Session session){
        super();
        this.session = session;
    }

    @Override
    public void guardar(Empleado empleado){
        Transaction trans = session.beginTransaction();
        session.persist(empleado);
        trans.commit();
    }
}
