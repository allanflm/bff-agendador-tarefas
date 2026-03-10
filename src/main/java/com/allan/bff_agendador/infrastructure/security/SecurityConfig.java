package com.allan.bff_agendador.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = SecurityConfig.SECURTY_SCHEME, type = SecuritySchemeType.HTTP,
bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {
    public static final String SECURTY_SCHEME = "BearerAuth";

}
