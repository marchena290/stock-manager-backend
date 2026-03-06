package com.inventario.stock_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventario.stock_manager.model.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor vacío que Jackson NECESITA para el 400
@AllArgsConstructor // Genera un constructor con todos los campos
@Builder // Por si quieres crear objetos de forma elegante en tus tests
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockActual;
    private Integer stockMinimo;
    private String imageUrl;
    private Boolean isCritical;
    private ProductStatus status;
    private LocalDateTime createdAt;
}