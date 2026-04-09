package com.Codigo.GestorSB.Service;

import com.Codigo.GestorSB.DTO.DeptoDTO;
import com.Codigo.GestorSB.Entity.Depto;
import com.Codigo.GestorSB.Repository.DeptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeptoService {

    @Autowired
    public DeptoRepository deptoRepository;

    public List<DeptoDTO> listarDepartamentos(){
        List<Depto> departamentos = deptoRepository.findAll();
        List<DeptoDTO> depto = new ArrayList<>();

        for(Depto dep : departamentos){
            DeptoDTO deptoDTO = new DeptoDTO(dep.getNumdep(), dep.getNomdep());
            depto.add(deptoDTO);
        }
        return depto;
    }

    public Optional<Depto> mostrarDepartamento(BigDecimal id){
        return deptoRepository.findById(id);
    }
}
