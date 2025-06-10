package de.upteams.sortmeister.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI trashSortOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trash Sort API")
                        .description("REST API documentation for Trash Sort Application")
                        .version("v1.0"));
    }
}
