package org.example.Repositorios;

import jakarta.persistence.Query;
import org.example.Entidades.Departamento;
import org.example.Entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
        try {


            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void filtrarPuesto(String puesto){
        Transaction trans = session.beginTransaction();
        try{
           Query query = session.createQuery("FROM Empleado emp WHERE emp.puesto =:puesto");
           query.setParameter("puesto",puesto);

           List<Empleado> empList = query.getResultList();

            System.out.println("EMPLEADOS CON EL PUESTO "+puesto+":");
            for (Empleado empleado : empList){
                System.out.println(empleado.toString());
            }

            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void mayorSueldo(){
        try{
            Empleado empleado = (Empleado) session.createQuery("FROM Empleado emp ORDER BY emp.sueldo DESC").setMaxResults(1).uniqueResult();
            System.out.println(empleado.toString());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void subirSueldoJefe(double cantidad){
        Transaction trans = session.beginTransaction();
        try{
            Query query = session.createQuery("UPDATE Empleado emp SET sueldo = sueldo + :cantidad WHERE esJefe = True");
            query.setParameter("cantidad",cantidad);
            query.executeUpdate();
            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    public void eliminarPorIdDep(int id){
        Transaction trans = session.beginTransaction();
        try{
            Query query = session.createQuery("DELETE FROM Empleado emp WHERE emp.departamento.id_departamento =:id");
            query.setParameter("id",id);
            int contador = query.executeUpdate();
            if(contador>0) System.out.println("SE ELIMINO "+contador+" EMPLEADOS DEL DEPARTAMENTO CON ID "+id);
            else System.out.println("NO SE ENCONTRO DEPARTAMENTO CON ID: "+id);

            trans.commit();
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }

    @Override
    public void guardar(Empleado empleado){
        Transaction trans = session.beginTransaction();
        try {
            session.persist(empleado);
            trans.commit();
            System.out.println("SE GUARDO CORRECTAMENTE EMPLEADO: "+empleado.getNombre());
        }catch (Exception e){
            System.err.println(e.getMessage());
            trans.rollback();
        }
    }
}
