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
                // 1. Ruta para Usuarios (MySQL + REST)
                .route("usuarios-service", r -> r.path("/api/usuarios/**")
                        .uri("lb://usuarios-service"))

                // 2. Ruta para Reservas (MySQL + REST)
                .route("reservas-service", r -> r.path("/api/reservas/**")
                        .uri("lb://reservas-service"))

                // 3. Ruta para Comentarios (MongoDB + GraphQL)
                .route("comentarios-service", r -> r.path("/graphql/**")
                        .uri("lb://comentarios-service"))

                .build();
    }
}
