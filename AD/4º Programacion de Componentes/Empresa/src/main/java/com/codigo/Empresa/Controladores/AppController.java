package com.codigo.Empresa.Controladores;


import com.codigo.Empresa.Entidades.*;
import com.codigo.Empresa.Servicios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class AppController {

    private final ServiceDepto deptoService;
    private final ServiceEmp empService;

    @GetMapping("/Consulta1/{nomDep1}-{nomDep2}")
    public ResponseEntity<List<DeptoDTO>> findByNomdepIsNotAndNomdepIsNot(@PathVariable String nomDep1, @PathVariable String nomDep2) {
        List<DeptoDTO> deptos = deptoService.findByNomdepIsNotAndNomdepIsNot(nomDep1, nomDep2)
                .stream()
                .map(DeptoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deptos);
    }

    @GetMapping("/Consulta4")
    public ResponseEntity<List<String>> empleadosComision() {
        return ResponseEntity.ok(empService.findByCominionNotNull());
    }

    @GetMapping("/Consulta5/{id}")
    public ResponseEntity<List<EmpDTO>> empleadosSalarioMayorASalariodeOtroCompanhero(@PathVariable Long id) {
        List<EmpDTO> listaEmpDto = empService.empleadosSalarioMayorASalariodeOtroCompanhero(id).stream()
                .map(EmpDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listaEmpDto);
    }


    @GetMapping("/asignar/{idDept}-{idEmp}")
    public ResponseEntity<DeptoDTO> asignarDepartamento(@PathVariable Long idDept, @PathVariable Long idEmp) {
        Depto depto = deptoService.findById(idDept);
        Emp emp = empService.findById(idEmp);
        depto.agregarEmpleado(emp);
        deptoService.guardar(depto);
        return ResponseEntity.ok(new DeptoDTO(depto));
    }

    @GetMapping("/jefe/{idDept}-{idEmp}")
    public ResponseEntity<DeptoDTO> modificarJefe(@PathVariable Long idDept, @PathVariable Long idEmp) {
        Depto depto = deptoService.findById(idDept);
        Emp emp = empService.findById(idEmp);
        depto.agregarJefe(emp);
        deptoService.guardar(depto);
        return ResponseEntity.ok(new DeptoDTO(depto));
    }
}
