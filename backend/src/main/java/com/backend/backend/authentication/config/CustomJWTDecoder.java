package com.backend.backend.authentication.config;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import com.nimbusds.jose.JOSEException;


@Configuration
public class CustomJWTDecoder {
    private String secret = "A5D8TnYhn/epdtTNU0VMVWp/WSGoYsd/8As0Joh+ddsIxszLlRB1RiDZeUnlDvAZ26YADU37AyUwiL2ROfpgyA==";
    @Bean
    JwtDecoder jwtDecoder() throws JOSEException{
        SecretKey spec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(spec).build();
    }
}
