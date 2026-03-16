package com.codigo.Saludo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saludo")
public class ControllerSaludo {

    private final ServiceSaludo serviceSaludo;

    @Autowired
    public ControllerSaludo(ServiceSaludo serviceSaludo){
        this.serviceSaludo = serviceSaludo;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<String> saludo(@PathVariable String nombre){
        String saludo = serviceSaludo.saludarUsuario(nombre);
        return ResponseEntity.ok(saludo);
    }

    @PostMapping("/{nombre}-{idioma}")
    public ResponseEntity<String> saludarPorIdioma(@PathVariable String nombre, @PathVariable String idioma){
        String saludo = serviceSaludo.saludarPorIdioma(nombre, idioma);
        return ResponseEntity.ok(saludo);
    }

    @PostMapping("/cuerpo")
    public ResponseEntity<String> saludo2(@PathVariable String nombre){
        String saludo = serviceSaludo.saludarUsuario(nombre);
        return ResponseEntity.ok(saludo);
    }
}
