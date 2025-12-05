package org.example.Repositorios;

import jakarta.persistence.Query;
import org.example.Entidades.Departamento;
import org.example.Entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepEmpleado implements Repositorio<Empleado> {

    private Session session;

    public RepEmpleado(Session session){
        super();
        this.session = session;
    }

    public void agregarEmpleado(int idDepartamento, String nombre,String puesto, double sueldo, int edad, String DNI){
        Transaction trans = session.beginTransaction();

        Departamento dep = (Departamento) session.createQuery("FROM Departamento dep WHERE dep.id_departamento=:id").setParameter("id",idDepartamento).uniqueResult();

        Empleado newEmpleado = new Empleado(nombre,puesto,sueldo,edad,DNI,false);
        newEmpleado.setDepartamento(dep);

        session.persist(newEmpleado);

        dep.addEmpleado(newEmpleado);

        trans.commit();
    }

    public void modificarJefe(int idDep, int idEmp){
        Transaction trans = session.beginTransaction();

        trans.commit();
    }

    @Override
    public void guardar(Empleado empleado){
        Transaction trans = session.beginTransaction();
        session.persist(empleado);
        trans.commit();
    }
}
