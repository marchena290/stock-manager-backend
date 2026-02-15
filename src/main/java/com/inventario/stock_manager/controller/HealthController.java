package com.inventario.stock_manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.stock_manager.dto.HealthResponse;
import com.inventario.stock_manager.service.HealthService;

/**
 * Controlador del endpoint de salud. Expone GET /health para comprobar que la aplicación está en marcha.
 */
@RestController
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    /**
     * GET /health → 200 OK con cuerpo {"status":"UP"}.
     */
    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(healthService.getStatus());
    }
}
