package com.backend.backend.authentication.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class CustomKeyPair {
    @Bean
    KeyPair keyPair() throws Exception{
        try {
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            log.info("Fault in keypair generator." + " " + getClass().descriptorString());
            throw new Exception("Fault in keypair generator." + " " + getClass().descriptorString());
        }
    }
}
