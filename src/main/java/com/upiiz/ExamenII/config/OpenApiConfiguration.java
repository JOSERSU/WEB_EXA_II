package com.upiiz.ExamenII.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
// Personalizar la documentación de nuestra api
@OpenAPIDefinition(
        info = @Info(
                title = "API de Review",
                description = "Esta API proporciona acceso a los recursos de la API de Reviews",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jose Refugio Salinas Uribe",
                        email = "jsalinasu1800@alumno.ipn.mx",
                        url = "http://localhost:8081/contacto"
                ),
                license = @License(),
                termsOfService = "Solo permiten 400 solicitudes diarias"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Servidor de Produccion",
                         url = "https://web-exa-ii.onrender.com/api/v1/reviews"
                )
        },
        tags = {
                @Tag(
                        name = "Reviews",
                        description = "Endpoints para los recursos de reviews "
                )
        }
)
public class OpenApiConfiguration {
}
