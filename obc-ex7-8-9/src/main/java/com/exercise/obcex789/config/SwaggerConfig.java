package com.exercise.obcex789.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuración Swagger para la generación de documentacion de la API REST
 *
 * HTML http://localhost:8081/swagger-ui/
 * JSON http://localhost:8081/v2/api-docs
 */

@Configuration
public class SwaggerConfig {

    //objeto Docket es un builder para hacer de interface principal de Springfox
    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Datos de documentación de la API
     * @return
     */
    private ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot laptops API REST",
                "Library Api REST docs",
                "1.0",
                "http://google.com",
                new Contact("Raul",
                        "http://google.com",
                        "raul@example.com"),
                "MIT",
                "http://google.com",
                Collections.emptyList());
    }
}
