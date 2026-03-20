package com.backend.backend.authentication.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class CustomJWKSource {
    @Bean
    JWKSource<SecurityContext> jwkSource(RSAKey rsaKey){
        var jwkSet=new JWKSet(rsaKey);
        return (jwkSelector,context)->jwkSelector.select(jwkSet);
    }
}
