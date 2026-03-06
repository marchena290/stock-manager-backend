package com.inventario.stock_manager.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada para la creación de productos (ST-04).
 * Solo contiene los campos que el cliente puede enviar.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Positive
    private BigDecimal price;

    @Min(0)
    private Integer stockActual;

    @Min(0)
    private Integer stockMinimo;

    /**
     * URL opcional si el cliente ya tiene una imagen subida
     * y solo quiere referenciarla. Si se envía un archivo,
     * Cloudinary tendrá prioridad sobre este campo.
     */
    private String imageUrl;
}

