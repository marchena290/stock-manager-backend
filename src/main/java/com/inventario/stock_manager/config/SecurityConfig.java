package com.inventario.stock_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Desactivamos CSRF para pruebas de API
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/health").permitAll() // Permitir explícitamente
                .anyRequest().authenticated()           // Bloquear todo lo demás
            )
            .httpBasic(Customizer.withDefaults()); // Configuración básica estándar

        return http.build();
    }
}