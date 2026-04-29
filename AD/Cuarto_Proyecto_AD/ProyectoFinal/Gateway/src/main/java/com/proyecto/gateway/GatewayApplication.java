package com.proyecto.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 1. Ruta para Usuarios (Raíz /usuarios)
                .route("usuarios-service", r -> r.path("/usuarios/**")
                        .uri("lb://usuarios")) // Asegúrate de que el micro de usuarios se llame "usuarios" en su properties

                // 2. Ruta para Reservas (Raíz /reservas)
                .route("reservas-service", r -> r.path("/reservas/**")
                        .uri("lb://reservas"))

                // 3. Ruta para Comentarios (Endpoint /comentarios para GraphQL/GraphIQL)
                .route("comentarios-service", r -> r.path("/comentarios/**")
                        .uri("lb://comentarios"))
                .build();
    }
}