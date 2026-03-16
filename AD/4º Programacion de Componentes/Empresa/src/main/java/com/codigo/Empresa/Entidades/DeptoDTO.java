package com.codigo.Empresa.Entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptoDTO {

    private Long numdep;
    private String nomdep;
    private String localidad;
    private Long codJefe;

    public DeptoDTO(Depto depto){
        this.numdep = depto.getNumdep();
        this.nomdep = depto.getNomdep();
        this.localidad = depto.getLocalidad();
        this.codJefe = depto.getCodjefe().getNumemp();
    }
}
