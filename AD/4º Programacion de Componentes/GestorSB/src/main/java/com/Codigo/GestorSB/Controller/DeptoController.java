package com.Codigo.GestorSB.Controller;

import com.Codigo.GestorSB.DTO.DeptoDTO;
import com.Codigo.GestorSB.Entity.Depto;
import com.Codigo.GestorSB.Service.DeptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Departamentos")
public class DeptoController {

    @Autowired
    public DeptoService deptoService;

    @GetMapping("/listar")
    public ResponseEntity<List<DeptoDTO>> listarDepartamentos(){
        try {
            List<DeptoDTO> departamentos = deptoService.listarDepartamentos();
            if(departamentos.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(departamentos);
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeptoDTO> obtenerDepartamento(@PathVariable BigDecimal id){
        Optional<Depto> departamento = deptoService.mostrarDepartamento(id);
        if(departamento.isPresent()){
            Depto dep = departamento.get();
            DeptoDTO dto = new DeptoDTO(dep.getNumdep(), dep.getNomdep());
            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}