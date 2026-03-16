package com.codigo.Empresa.Controladores;

import com.codigo.Empresa.Entidades.Emp;
import com.codigo.Empresa.Servicios.ServiceEmp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados/emp")
@RequiredArgsConstructor
public class EmpController {

    private final ServiceEmp empService;

    @GetMapping
    public List<Emp> obtenerEmpleados() {
        return empService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> obtenerEmpleadoPorId(@PathVariable Long id) {
        Emp empleado = empService.findById(id);
        return ResponseEntity.ok(empleado);
    }

    @PostMapping
    public ResponseEntity<String> crearEmpleado(@RequestBody Emp empleado) {
        empService.guardar(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body("Empleado guardado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emp> modificarEmpleado(@PathVariable Long id, @RequestBody Emp empleadoActualizado) {
        Emp empleado = empService.findById(id);
        if (empleado != null) {
            empleadoActualizado.setNumemp(id);
            empService.guardar(empleadoActualizado);
            return ResponseEntity.ok(empleadoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}