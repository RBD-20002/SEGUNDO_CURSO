package com.Codigo.GestorSB.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DeptoDTO {
    private BigDecimal numdep;
    private String nomdep;
    private String localidad;
    private BigDecimal codjefe;

    public DeptoDTO(BigDecimal numdep, String nomdep) {
        this.numdep = numdep;
        this.nomdep = nomdep;
    }
}