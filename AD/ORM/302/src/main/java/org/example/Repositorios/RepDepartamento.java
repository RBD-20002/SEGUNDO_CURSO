package org.example.Repositorios;

import jakarta.persistence.Query;
import org.example.Entidades.Departamento;
import org.example.Entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepDepartamento implements Repositorio<Departamento> {

    private Session session;

    public RepDepartamento(Session session){
        super();
        this.session = session;
    }

    public void verDepartamento(int id){
        Departamento dep = (Departamento) session.createQuery("FROM Departamento dep WHERE id_departamento ="+id).uniqueResult();
        System.out.println(dep.toString());

        Query query = session.createQuery("FROM Empleado emp WHERE emp.departamento.id_departamento ="+id);
        List<Empleado> empDep = query.getResultList();

        for(Empleado empleado : empDep){
            System.out.println(empleado.toString());
        }
    }

    public void eliminarDepartamento(int id){
        Transaction trans = session.beginTransaction();
        Departamento dep = (Departamento) session.createQuery("FROM Departamento dep WHERE id_departamento ="+id).uniqueResult();
        session.remove(dep);
        trans.commit();
    }

    @Override
    public void guardar(Departamento departamento) {
        Transaction trans = session.beginTransaction();
        session.persist(departamento);
        trans.commit();
    }
}
