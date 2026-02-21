package com.inventario.stock_manager.security;

import com.inventario.stock_manager.config.JwtProperties;
import com.inventario.stock_manager.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Servicio para generar y validar tokens JWT. Usa el secret y la expiración definidos en application.yml.
 */
@Service
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey key;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Genera un JWT con subject (username) y claim "role". Expira según app.jwt.expiration-ms.
     */
    public String generateToken(String username, Role role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(username)
                .claim("role", role.name())
                .issuedAt(new Date(now))
                .expiration(new Date(now + jwtProperties.expirationMs()))
                .signWith(key)
                .compact();
    }

    /**
     * Valida el token y devuelve el subject (username). Lanza si el token es inválido o expirado.
     */
    public String validateAndGetUsername(String token) {
        return validateAndGetClaims(token).getSubject();
    }

    /**
     * Valida el token y devuelve los claims. Útil para obtener role y construir autoridades.
     */
    public Claims validateAndGetClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
