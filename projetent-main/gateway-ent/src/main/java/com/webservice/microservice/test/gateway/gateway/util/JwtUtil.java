package com.webservice.microservice.test.gateway.gateway.util;

import com.webservice.microservice.test.gateway.gateway.exception.JwtTokenMalformedException;
import com.webservice.microservice.test.gateway.gateway.exception.JwtTokenMissingException;
import com.webservice.microservice.test.gateway.gateway.filter.FilterWithAuthorities;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public Claims getClaims(final String token, ServerHttpRequest request) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

            System.out.println(body);
            String subject = body.getSubject();
            String role = subject.split("&")[1];
            FilterWithAuthorities.filterAuthirities(request.getPath().toString(), role);
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
            return null;
        }

    }

    public void validateToken(final String token, ServerHttpRequest request) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            getClaims(token, request);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature", HttpStatus.BAD_REQUEST);
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.", HttpStatus.BAD_REQUEST);
        }
    }
}
