package org.example;

import org.example.Entidades.Departamento;
import org.example.Entidades.Empleado;
import org.example.Repositorios.RepDepartamento;
import org.example.Repositorios.RepEmpleado;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        System.out.println("Ejecutando ORM: ");

        Session session = HibernateUtil.get().openSession();

        RepEmpleado empRepo = new RepEmpleado(session);
        RepDepartamento depRepo = new RepDepartamento(session);

        Departamento dp1 = new Departamento("Departamento 1", "vigo");
        Departamento dp2 = new Departamento("Departamento 2", "santiago");
        Departamento dp3 = new Departamento("Departamento 3", "lugo");

        Empleado emp1 = new Empleado("Emp 1", "Ingeniero", 3000, 45, "12345678A", true);
        Empleado emp2 = new Empleado("Emp 2", "Tecnico", 1000, 18, "12345678B", false);
        Empleado emp3 = new Empleado("Emp 3", "Ingeniero", 4000, 50, "12345678C", true);
        Empleado emp4 = new Empleado("Emp 4", "Tecnico", 1500, 30, "12345678D", false);
        Empleado emp5 = new Empleado("Emp 5", "Ingeniero", 2000, 35, "12345678E", false);
        Empleado emp6 = new Empleado("Emp 6", "Tecnico", 1250, 20, "12345678F", false);

        System.out.println("SE AGREGO EMPLEADOS");
        dp1.addEmpleado(emp1);
        dp2.addEmpleado(emp3);
        dp3.addEmpleado(emp5);

        depRepo.guardar(dp1);
        depRepo.guardar(dp2);
        depRepo.guardar(dp3);

        System.out.println("-".repeat(20));

        depRepo.verDepartamento(2);

        System.out.println("-".repeat(20));

        empRepo.agregarEmpleado(1,"Ricardo","Jefe",1500.00,23,"Y9304520E");

        System.out.println("-".repeat(20));

        depRepo.eliminarDepartamento(3);
    }
}