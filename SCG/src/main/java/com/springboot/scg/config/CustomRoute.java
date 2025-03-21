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
                .route("user-server", r -> r.path("/user/**")
                        .uri("lb://user-server"))
                .route("user-server", r -> r.path("/user/patch/{userId}")
                        .filters(f -> f.filter(jwtFilter.apply(new AuthorizationHeaderFilter.Config())))
                        .uri("lb://user-server"))
                .route("user-server", r -> r.path("/user/delete/{userId}")
                        .filters(f -> f.filter(jwtFilter.apply(new AuthorizationHeaderFilter.Config())))
                        .uri("lb://user-server"))
                .route("user-server", r -> r.path("/user/profile/{userId}")
                        .filters(f -> f.filter(jwtFilter.apply(new AuthorizationHeaderFilter.Config())))
                        .uri("lb://user-server"))


                .route("product-server", r -> r.path("/product/**")
                        .uri("lb://product-server"))

                .route("product-server", r -> r.path("/menu/**")
                        .uri("lb://product-server"))

                .route("product-server", r -> r.path("/category/**")
                        .uri("lb://product-server"))

                .route("review-server", r -> r.path("/review/**")
                        .uri("lb://review-server"))

                .route("payment-server", r-> r.path("/payment/**")
                        .uri("lb://payment-server"))

                .build();
    }
}
