package com.Codigo.GestorSB.Controller;

import com.Codigo.GestorSB.DTO.EmpDTO;
import com.Codigo.GestorSB.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/Empleados")
public class EmpController {

    @Autowired
    public EmpService empService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<EmpDTO>> filtrarEmpleados(@RequestParam BigDecimal salario){
        try{
            List<EmpDTO> resultado = empService.filtrarEmpleadosSegunSalario(salario);
            if(resultado.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(resultado);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarEmpleadoPorPuesto(@RequestParam String puesto){
        try{
            int filasAfectado = empService.eliminarEmpleadoPorPuesto(puesto);
            if(filasAfectado > 0){
                return ResponseEntity.ok("Se eliminaron "+filasAfectado+" empleados con el puesto: "+puesto);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}