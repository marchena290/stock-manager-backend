package com.inventario.stock_manager.dto;

import com.inventario.stock_manager.model.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO inmutable para la entidad Product. Refleja el modelo del dominio (precio, stock, criticidad, estado).
 */
public record ProductDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockActual,
        Integer stockMinimo,
        String imageUrl,
        Boolean isCritical,
        ProductStatus status,
        LocalDateTime createdAt
) {}
