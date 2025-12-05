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
        Empleado emp7 = new Empleado("Emp 7","Tecnico",10000,23,"Y9304520E",false);
        Empleado emp8 = new Empleado("Emp 8","Tecnico",25000,25,"75667682",false);
        Empleado emp9 = new Empleado("Emp 9","Ingeniero",4500,45,"7898452A",false);
        Empleado emp10 = new Empleado("Emp 10","Recursos Humanos",2200,23,"45678325A",false);
        Empleado emp11 = new Empleado("Emp 11","Recursos Humanos",2500,25,"45678785A",false);
        Empleado emp12 = new Empleado("Emp 12","Recursos Humanos",2800,28,"45358785A",false);

        System.out.println("EMPLEADOS AGREGADO A DEP 1:");
        dp1.addEmpleado(emp1);
        dp1.addEmpleado(emp3);
        dp1.addEmpleado(emp5);
        dp1.addEmpleado(emp9);

        System.out.println("-".repeat(50));

        System.out.println("EMPLEADOS AGREGADO A DEP 2:");
        dp2.addEmpleado(emp2);
        dp2.addEmpleado(emp4);
        dp2.addEmpleado(emp6);
        dp2.addEmpleado(emp7);
        dp2.addEmpleado(emp8);

        System.out.println("-".repeat(50));

        System.out.println("EMEPLADOS AGREGADOS A DEP 3:");
        dp3.addEmpleado(emp10);
        dp3.addEmpleado(emp11);
        dp3.addEmpleado(emp12);

        System.out.println("-".repeat(50));

        System.out.println("DEPARTAMENTOS GUARDADOS:");
        depRepo.guardar(dp1);
        depRepo.guardar(dp2);
        depRepo.guardar(dp3);

        System.out.println("-".repeat(50));

        System.out.println("EMPLEADOS QUE SON TECNICOS");
        empRepo.filtrarPuesto("Tecnico");

        System.out.println("-".repeat(50));

        System.out.println("EMPLEADOS QUE SON INGENIEROS:");
        empRepo.filtrarPuesto("Ingeniero");

        System.out.println("-".repeat(50));

        System.out.println("EMPLEADOS QUE SON RRHH:");
        empRepo.filtrarPuesto("Recursos Humanos");

        System.out.println("-".repeat(50));

        System.out.println("EMPLEADO CON MAYOR SUELDO: ");
        empRepo.mayorSueldo();

        System.out.println("-".repeat(50));

        System.out.println("JEFES ANTES DEL AUMENTO:");
        System.out.println(emp1.toString());
        System.out.println(emp3.toString());

        System.out.println("-".repeat(50));

        System.out.println("JEFES DESPUES DEL AUMENTO: ");
        empRepo.subirSueldoJefe(500);

        session.refresh(emp1);
        session.refresh(emp3);

        System.out.println(emp1.toString());
        System.out.println(emp3.toString());

        System.out.println("-".repeat(50));

        System.out.println("DEPUES DE ELIMINAR UN DEPARTAMENTO: ");
        empRepo.eliminarPorIdDep(3);
    }
}