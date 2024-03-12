package com.webservice.microservice.test.gateway.gateway.filter;

import com.webservice.microservice.test.gateway.gateway.exception.IsjException;
import com.webservice.microservice.test.gateway.gateway.exception.JwtTokenMalformedException;
import com.webservice.microservice.test.gateway.gateway.exception.JwtTokenMissingException;
import com.webservice.microservice.test.gateway.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = new ArrayList<>();

        apiEndpoints.add("/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        System.out.println("path: "+request.getPath().toString());

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            final String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);
            String token = authHeader;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                System.out.println(authHeader);
                token = authHeader.substring(7);
            }
            try {
                jwtUtil.validateToken(token, request);
            } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                e.printStackTrace();
                //ResponseEntity response1 = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);

                return response.setComplete();
            }

            Claims claims = jwtUtil.getClaims(token, request);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
        }

        return chain.filter(exchange);
    }

}
