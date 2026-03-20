package com.backend.backend.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class CustomJWTEncoder {
    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext>context){
        return new NimbusJwtEncoder(context);
    }
}
