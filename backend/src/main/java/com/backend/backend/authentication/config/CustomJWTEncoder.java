package com.backend.backend.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class CustomJWTEncoder {
    private String secret = "A5D8TnYhn/epdtTNU0VMVWp/WSGoYsd/8As0Joh+ddsIxszLlRB1RiDZeUnlDvAZ26YADU37AyUwiL2ROfpgyA==";
    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext>context){
        JWK jwk = new OctetSequenceKey.Builder(secret.getBytes())
                .algorithm(JWSAlgorithm.HS256)
                .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
