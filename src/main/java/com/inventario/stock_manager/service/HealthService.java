package com.inventario.stock_manager.service;

import com.inventario.stock_manager.dto.HealthResponse;
import org.springframework.stereotype.Service;

/**
 * Servicio de salud de la aplicación. Encapsula la lógica del estado UP para el endpoint /health.
 */
@Service
public class HealthService {

    private static final String STATUS_UP = "UP";

    /**
     * Indica que la aplicación está en marcha. Útil para balanceadores y orquestadores.
     */
    public HealthResponse getStatus() {
        return new HealthResponse(STATUS_UP);
    }
}
