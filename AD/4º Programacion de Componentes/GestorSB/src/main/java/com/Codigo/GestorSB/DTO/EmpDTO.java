package com.Codigo.GestorSB.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmpDTO {
    private BigDecimal numemp;
    private String nomemp;
    private String puesto;
    private LocalDate feccont;
    private BigDecimal sal;
    private BigDecimal comision;
    private BigDecimal numdep;

    public EmpDTO(String nomemp, BigDecimal sal) {
        this.nomemp = nomemp;
        this.sal = sal;
    }
}