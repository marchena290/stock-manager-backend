package com.inventario.stock_manager.service;

import com.inventario.stock_manager.dto.LoginRequest;
import com.inventario.stock_manager.dto.LoginResponse;
import com.inventario.stock_manager.entity.User;
import com.inventario.stock_manager.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación: valida credenciales y genera el JWT.
 */
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Autentica con username/password (BCrypt se valida en el UserDetailsService) y devuelve el JWT.
     */
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        User user = (User) auth.getPrincipal();
        String token = jwtService.generateToken(user.getUsername(), user.getRole());
        return LoginResponse.of(token, user.getUsername(), user.getRole());
    }
}
