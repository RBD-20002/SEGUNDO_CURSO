package com.codigo.Empresa.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmpDTO {
    private Long numemp;
    private String nomemp;
    private String puesto;
    private LocalDate feccont;
    private float sal;
    private float comision;
    private Long numdep;

    public EmpDTO(Emp emp){
        this.numemp = emp.getNumemp();
        this.nomemp = emp.getNomemp();
        this.puesto = emp.getPuesto();
        this.feccont = emp.getFeccont();
        this.sal = emp.getSal();
        this.comision = emp.getComision();
        this.numdep = emp.getDeptoJefe().getNumdep();
    }
}
