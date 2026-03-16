package com.codigo.Saludo2;

import org.springframework.stereotype.Service;

@Service
public class ServiceSaludo {

    public String saludarUsuario(String nombre){
        return "Hola "+nombre+" como estas de nuevo?";
    }

    public String saludarPorIdioma(String nombre, String idioma){
        switch (idioma){
            case "Español":{
                return "Hola "+nombre+", te respondo en español";
            }
            case "Ingles":{
                return "Hello "+nombre+", i'll answer you in english.";
            }
            case "Gallego":{
                return "Ola "+nombre+", respondereiche en galego";
            }
            case "Italiano":{
                return "Ciao "+nombre+", ti rispondo in italiano";
            }
            default:{
                return "El sistema no soporte el idioma";
            }
        }
    }
}