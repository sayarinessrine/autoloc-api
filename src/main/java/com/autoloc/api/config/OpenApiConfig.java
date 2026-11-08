package com.autoloc.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autolocOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API AutoLoc")
                .description("API REST de gestion de location de véhicules (agences, véhicules, clients, "
                        + "réservations, contrats, paiements...)")
                .version("1.0.0"));
    }
}
