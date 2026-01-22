package org.example.Repositorios;

import org.example.Entidades.Departamento;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepDepartamento implements Repositorio<Departamento> {

    private Session session;

    public RepDepartamento(Session session){
        super();
        this.session = session;
    }
    @Override
    public void guardar(Departamento departamento) {
        Transaction trans = session.beginTransaction();
        session.persist(departamento);
        trans.commit();
    }
}
