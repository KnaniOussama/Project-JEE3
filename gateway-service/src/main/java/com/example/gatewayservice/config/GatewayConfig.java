package com.example.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://auth-service"))
                .route("agent-ia-service", r -> r.path("/api/chat/**")
                        .filters(f -> f.stripPrefix(1).filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://agent-ia-service"))
                .route("product-service", r -> r.path("/api/products/**")
                        .filters(f -> f.stripPrefix(1).filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://product-service"))
                .route("stock-service", r -> r.path("/api/stocks/**")
                        .filters(f -> f.stripPrefix(1).filter(filter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://stock-service"))
                .build();
    }
}
