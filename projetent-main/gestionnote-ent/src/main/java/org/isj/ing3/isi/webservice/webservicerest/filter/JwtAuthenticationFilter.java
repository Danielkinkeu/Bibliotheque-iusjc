package org.isj.ing3.isi.webservice.webservicerest.filter;


import io.jsonwebtoken.Claims;
import org.isj.ing3.isi.webservice.webservicerest.exception.JwtTokenMalformedException;
import org.isj.ing3.isi.webservice.webservicerest.exception.JwtTokenMissingException;
import org.isj.ing3.isi.webservice.webservicerest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {

            String jwt = getJwt(request);
            jwtUtil.validateToken(jwt);
        } catch (JwtTokenMalformedException | JwtTokenMissingException e) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println(authHeader);
            return authHeader.substring(7);
        }

        return null;
    }
}
