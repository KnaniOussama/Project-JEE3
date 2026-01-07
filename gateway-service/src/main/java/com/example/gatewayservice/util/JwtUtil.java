package com.example.gatewayservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public void validateToken(final String token) {
        Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token);
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
