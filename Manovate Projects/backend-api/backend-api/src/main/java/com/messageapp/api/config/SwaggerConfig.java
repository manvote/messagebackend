package com.messageapp.api.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        return new OpenAPI()
                .info(new Info()
                        .title("Message App Backend API")
                        .description("OTP + JWT Authentication APIs")
                        .version("1.0.0")
                )
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", bearerScheme)
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("BearerAuth"));
    }
}
