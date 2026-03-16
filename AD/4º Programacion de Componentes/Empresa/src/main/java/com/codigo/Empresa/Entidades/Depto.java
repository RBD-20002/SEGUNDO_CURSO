package com.codigo.Empresa.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "depto")
public class Depto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numdep", columnDefinition = "DECIMAL")
    private Long numdep;

    @Column(name = "nomdep", length = 14)
    private String nomdep;

    @Column(name = "localidad", length = 13)
    private String localidad;

    @OneToOne
    @JoinColumn(name = "codjefe")
    private Emp codjefe;

    @OneToMany(mappedBy = "depto")
    private List<Emp> empleados = new ArrayList<>();

    public void agregarEmpleado(Emp emp){
        this.empleados.add(emp);
    }

    public void agregarJefe(Emp emp){
        this.codjefe = emp;
        emp.setDeptoJefe(this);
    }
}
