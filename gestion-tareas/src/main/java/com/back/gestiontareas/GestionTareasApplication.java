package com.back.gestiontareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.back.gestiontareas.model.Usuario;
import com.back.gestiontareas.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionTareasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionTareasApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                usuarioRepository.save(
                        Usuario.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin123"))
                                .build()
                );
                System.out.println(" Usuario 'admin' creado con contraseña 'admin123'");
            } else {
                System.out.println(" Usuario 'admin' ya existe");
            }
        };
    }
}



