package com.backend.backend.authentication.config;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.jwk.RSAKey;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class CustomRSAKey {
    @Bean
    RSAKey rsaKey(KeyPair keyPair){
        return new RSAKey
        .Builder((RSAPublicKey)keyPair.getPublic())
        .privateKey(keyPair.getPrivate())
        .keyID(UUID.randomUUID().toString())
        .build();
    }
}
