package com.example.projet_2_spring_mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Contexte racine (partagé par toute l'application).
 * Scanne les couches service pour l'injection de dépendances (@Autowired).
 * Les @Controller sont exclus ici : ils sont gérés par le contexte servlet (WebConfig).
 */
@Configuration
@ComponentScan(
        basePackages = "com.example.projet_2_spring_mvc",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = org.springframework.stereotype.Controller.class)
)
public class RootConfig {
}
