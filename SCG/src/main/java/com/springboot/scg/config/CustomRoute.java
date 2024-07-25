package com.springboot.scg.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoute {

    @Bean
    public RouteLocator ms1Route(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("ms1", r -> r.path("/user/**")
                        .uri("http://localhost:8081"))
                .route("ms2", r -> r.path("/product/**")
                        .uri("http://localhost:8082"))
                .route("ms3", r -> r.path("/review/**")
                        .uri("http://localhost:8083"))
                .route("ms4", r -> r.path("/orders/**")
                        .uri("http://localhost:8084"))
                .build();
    }
}
