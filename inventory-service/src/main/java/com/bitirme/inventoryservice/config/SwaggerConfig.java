package com.bitirme.inventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class SwaggerConfig {
    String description = "Inventory Service";
    String version = "1.8";


    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory-Service")
                        .version(version)
                        .description(description)
                        .license(new License().name("Inventory-Service")));
    }




}
