package com.rkm.beprepared.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@OpenAPIDefinition(
        info = @Info(
                title = "BePrepared",
                description = "App permite informar aos usuários"+
                        "sobre situacoes climatologicas.",
                version ="1.0",
                contact = @Contact(
                        name = "Ruqueia Massangaia",
                        email = "ruqueia@gmail.com",
                        url = "https://github.com/Ruqueia"
                ),
                license = @License(
                        name = "Apache License 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
        )
@SecurityScheme(
        name = "Bearer Authentication",
        description = "Faça o login na API, para usar perfeitamente a BePrepared,"+
            "coloque o seu token de autenticaçao",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)


public class OpenApiConfig {
}
