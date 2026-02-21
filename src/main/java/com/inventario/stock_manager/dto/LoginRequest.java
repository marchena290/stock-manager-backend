package com.inventario.stock_manager.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de entrada para el login. Usado en POST /auth/login.
 */
public record LoginRequest(
        @NotBlank(message = "username no puede estar vacío") String username,
        @NotBlank(message = "password no puede estar vacío") String password
) {}
