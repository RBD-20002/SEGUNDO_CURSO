package com.codigo.Saludo;

import org.springframework.stereotype.Service;

@Service
public class ServiceSaludo {

    public String SaludarUsuario(String nombre){
        return "Hola "+nombre+" como estas?";
    }
}
