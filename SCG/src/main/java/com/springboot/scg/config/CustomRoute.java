package com.springboot.scg.config;

import com.springboot.scg.component.AuthorizationHeaderFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoute {
    private final AuthorizationHeaderFilter jwtFilter;

    public CustomRoute(AuthorizationHeaderFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public RouteLocator ms1Route(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("user-server", r -> r.path("/user/signup")
                        .uri("lb://user-server"))
                .route("user-server", r -> r.path("/user/signup/trainer")
                        .uri("lb://user-server"))
                .route("user-server", r -> r.path("/user/signin")
                        .uri("lb://user-server"))
                .route("auth-server", r -> r.path("/auth/**")
                        .filters(f -> f.filter(jwtFilter.apply(new AuthorizationHeaderFilter.Config())))
                        .uri("lb://auth-server"))
                .route("ms2", r -> r.path("/product/**")
                        .uri("http://localhost:8082"))
                .route("ms3", r -> r.path("/review/**")
                        .uri("http://localhost:8083"))
                .route("ms4", r -> r.path("/orders/**")
                        .uri("http://localhost:8084"))
                .build();
    }
}
