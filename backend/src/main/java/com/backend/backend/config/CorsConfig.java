package com.backend.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:3000", 
                            "http://localhost:5173"  ,
                            "http://127.0.0.1:5500",
                            "http://127.0.0.1:5501",
                            "http://127.0.0.1:5502",
                            "http://127.0.0.1:5503",
                            "http://127.0.0.1:5504",
                            "http://127.0.0.1:5505",
                            "http://127.0.0.1:5506",
                            "http://127.0.0.1:5507",
                            "http://127.0.0.1:5508",
                            "http://127.0.0.1:5509"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
                        .allowedHeaders("*") 
                        .allowCredentials(true); 
            }
        };
    }
}