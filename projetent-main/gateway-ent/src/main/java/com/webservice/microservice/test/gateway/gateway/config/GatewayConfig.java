package com.webservice.microservice.test.gateway.gateway.config;

import com.webservice.microservice.test.gateway.gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("api_oauth_route", r -> r.path("/oauth/**").filters(f -> f.filter(filter)).uri("http://localhost:9084/oauth"))
                .route("api_users_route", r -> r.path("/api/users-management/**").filters(f -> f.filter(filter)).uri("http://localhost:9087/api/users-management"))
                .route("api_gest_note", r -> r.path("/api/gest-note/**").filters(f -> f.filter(filter)).uri("http://localhost:9000/api/gest-note"))
                .route("hello", r -> r.path("/hello/**").filters(f -> f.filter(filter)).uri("lb://hello")).build();
    }
}
