package org.example.Entidades;

import jakarta.persistence.*;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado;

    private String nombre;
    private String puesto;
    private double sueldo;
    private int edad;
    private String DNI;
    private boolean esJefe;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(String nombre, String puesto, double sueldo, int edad, String DNI, boolean esJefe) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.edad = edad;
        this.DNI = DNI;
        this.esJefe = esJefe;
    }

    public int getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSueldo() {
        return sueldo;
    }
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public boolean isEsJefe() {
        return esJefe;
    }
    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String toString(){
        return "| ID: "+id_empleado+" |\n| NOMBRE: "+nombre+" |\n| PUESTO: "+puesto+" |\n| SUELDO: "+sueldo+" |\n| EDAD: "+edad+" |\n| DNI: "+DNI+" |\n| ID DEPARTAMENTO: "+departamento+" |";
    }
}
