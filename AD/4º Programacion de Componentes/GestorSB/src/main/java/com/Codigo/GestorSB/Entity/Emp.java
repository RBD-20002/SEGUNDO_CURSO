package com.Codigo.GestorSB.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "emp")
@Getter
@Setter
public class Emp {

    @Id
    @Column(name = "numemp", precision = 4)
    private BigDecimal numemp;

    @Column(name = "nomemp", columnDefinition = "VARCHAR", length = 10)
    private  String nomemp;

    @Column(name = "puesto", columnDefinition = "VARCHAR", length = 10)
    private String puesto;

    @Column(name = "feccont", columnDefinition = "DATE")
    private LocalDate feccont;

    @Column(name = "sal", precision = 7, scale = 2)
    private BigDecimal sal;

    @Column(name = "comision", precision = 7, scale = 2)
    private BigDecimal comision;

    @ManyToOne
    @JoinColumn(name = "numdep")
    private Depto depto;
}
