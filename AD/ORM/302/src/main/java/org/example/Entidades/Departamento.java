package org.example.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_departamento;

    private String nombre_departamento;
    private String localidad;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Empleado> empleadoList;

    public Departamento() {
    }

    public Departamento(String nombre_departamento, String localidad) {
        this.nombre_departamento = nombre_departamento;
        this.localidad = localidad;
        this.empleadoList = new ArrayList<>();
    }

    public int getId_departamento() {
        return id_departamento;
    }
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }
    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public List<Empleado> getEmpleadosList() {
        return empleadoList;
    }

    public void addEmpleado(Empleado empleado){
        empleadoList.add(empleado);
        empleado.setDepartamento(this);
    }

    @Override
    public String toString(){
        return "| ID DEPARTAMENTO: "+id_departamento+" |\n| DEPARTAMENTO: "+nombre_departamento+" |\n| LOCALIDAD: "+localidad+" |";
    }
}
