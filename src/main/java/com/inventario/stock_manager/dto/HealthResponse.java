package com.inventario.stock_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO inmutable para la respuesta del endpoint /health. Serializa como {"status":"UP"}.
 */
public record HealthResponse(
        @JsonProperty("status") String status
) {}
