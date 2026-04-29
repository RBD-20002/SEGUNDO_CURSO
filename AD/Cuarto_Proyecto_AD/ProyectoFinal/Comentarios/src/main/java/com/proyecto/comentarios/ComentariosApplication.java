package com.proyecto.comentarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ComentariosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComentariosApplication.class,args);
    }
}
