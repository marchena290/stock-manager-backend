package com.inventario.stock_manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propiedades de JWT bindeadas desde application.yml (app.jwt.*).
 * Permite usar ${JWT_SECRET} y ${JWT_EXPIRATION_MS} sin hardcodear.
 */
@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
        String secret,
        long expirationMs
) {}
