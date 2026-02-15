package com.inventario.stock_manager.model;

/**
 * Estado del producto en inventario. Se persiste con EnumType.STRING en JPA.
 */
public enum ProductStatus {
    ACTIVE,
    INACTIVE
}
