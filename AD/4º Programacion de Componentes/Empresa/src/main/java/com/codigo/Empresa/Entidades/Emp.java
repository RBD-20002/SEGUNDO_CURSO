package com.codigo.Empresa.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "emp")
public class Emp {

    @Id
    @Column(name = "numemp", columnDefinition = "DECIMAL")
    private Long numemp;

    @NonNull
    @Column(name = "nomemp", length = 10)
    private String nomemp;

    @NonNull
    @Column(name = "puesto", length = 10)
    private String puesto;

    @NonNull
    @Column(name = "feccont", columnDefinition = "DATE")
    private LocalDate feccont;

    @NonNull
    @Column(name = "sal", columnDefinition = "DECIMAL")
    private float sal;

    @Column(name = "comision", columnDefinition = "DECIMAL")
    private float comision;

    @ManyToOne
    @JoinColumn(name = "numdep")
    private Depto numdep;

    @OneToOne(mappedBy = "jefe")
    private Depto deptoJefe;

    public void agregarJefe(Depto depto){
        this.deptoJefe = depto;
        depto.setCodjefe(this);
    }
}
