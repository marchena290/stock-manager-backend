package com.inventario.stock_manager.dto;

import com.inventario.stock_manager.model.Role;

import java.time.LocalDateTime;

/**
 * DTO inmutable para la entidad User. No expone la contraseña.
 */
public record UserDTO(
        Long id,
        String username,
        Role role,
        LocalDateTime createdAt
) {}
