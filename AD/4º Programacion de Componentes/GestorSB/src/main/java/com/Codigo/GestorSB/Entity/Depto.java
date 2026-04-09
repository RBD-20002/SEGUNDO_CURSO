package com.Codigo.GestorSB.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "depto")
@Getter
@Setter
public class Depto {

    @Id
    @Column(name = "numdep", precision = 2, scale = 0)
    private BigDecimal numdep;

    @Column(name = "nomdep", columnDefinition = "VARCHAR", length = 14)
    private String nomdep;

    @Column(name = "localidad", columnDefinition = "VARCHAR", length = 13)
    private String localidad;

    @Column(name = "codjefe", precision = 4, scale = 0)
    private BigDecimal codjefe;

    @OneToMany(mappedBy = "depto")
    private List<Emp> empleados;
}
