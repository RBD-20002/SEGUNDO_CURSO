package com.codigo.Saludo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class ControllerSaludo {

    private final ServiceSaludo serviceSaludo;

    @Autowired
    public ControllerSaludo(ServiceSaludo serviceSaludo){
        this.serviceSaludo = serviceSaludo;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<String> saludar(@PathVariable String nombre){
        String saludo = serviceSaludo.SaludarUsuario(nombre);
        return ResponseEntity.ok(saludo);
    }
}
