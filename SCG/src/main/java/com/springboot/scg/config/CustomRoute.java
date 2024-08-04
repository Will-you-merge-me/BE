package com.springboot.scg.config;

import com.springboot.scg.component.AuthorizationHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
                .route("user-server", r -> r.path("/user/**")
                        .filters(f -> f.filter(jwtFilter.apply(new AuthorizationHeaderFilter.Config())))
                        .uri("lb://user-server"))

                .route("product-server", r -> r.path("/product/**")
                        .uri("lb://product-server"))

                .route("review-server", r -> r.path("/review/**")
                        .uri("lb://review-server"))

                .build();
    }
}
