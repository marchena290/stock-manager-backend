package com.inventario.stock_manager.config;

import com.inventario.stock_manager.entity.User;
import com.inventario.stock_manager.model.Role;
import com.inventario.stock_manager.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Crea un usuario administrador inicial si la tabla está vacía y están definidas
 * las variables de entorno INITIAL_ADMIN_USERNAME e INITIAL_ADMIN_PASSWORD (sin hardcodear).
 */
@Component
public class InitialUserLoader implements ApplicationRunner {

    private static final String INITIAL_ADMIN_USERNAME = "INITIAL_ADMIN_USERNAME";
    private static final String INITIAL_ADMIN_PASSWORD = "INITIAL_ADMIN_PASSWORD";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    public InitialUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, Environment environment) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            return;
        }
        String username = environment.getProperty(INITIAL_ADMIN_USERNAME);
        String password = environment.getProperty(INITIAL_ADMIN_PASSWORD);
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return;
        }
        if (userRepository.existsByUsername(username)) {
            return;
        }
        User admin = new User(username, passwordEncoder.encode(password), Role.ADMIN);
        userRepository.save(admin);
    }
}
