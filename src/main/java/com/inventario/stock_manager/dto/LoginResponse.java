package com.inventario.stock_manager.dto;

import com.inventario.stock_manager.model.Role;

/**
 * DTO de respuesta del login: token JWT y datos del usuario.
 */
public record LoginResponse(
        String token,
        String type,
        String username,
        Role role
) {
    public static LoginResponse of(String token, String username, Role role) {
        return new LoginResponse(token, "Bearer", username, role);
    }
}
