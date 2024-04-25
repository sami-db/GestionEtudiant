package org.projet.gestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configurer les chemins et les origines ici
                registry.addMapping("/**") // Cela applique CORS à toutes les routes
                        .allowedOrigins("http://localhost:4200", "http://localhost:4200/*") // Autorise cette origine pour les requêtes cross-origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Les méthodes HTTP autorisées
                        .allowedHeaders("*") // Autorise tous les headers
                        .allowCredentials(true); // Permet d'envoyer des informations d'identification
            }
        };
    }
}