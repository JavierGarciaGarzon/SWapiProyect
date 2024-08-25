package com.diverger.swapi.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("StarWars API customization")
                        .description("This API uses swapi, to process data and add some value information")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}