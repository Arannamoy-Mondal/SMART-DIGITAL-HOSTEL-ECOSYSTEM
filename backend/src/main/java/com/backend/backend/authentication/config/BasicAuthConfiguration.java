package com.backend.backend.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.authorizeHttpRequests(
            auth->auth.requestMatchers(
                "/user/signup",
                "/user/login"
                // "/v3/api-docs/**",
                // "/swagger-ui/**",
                // "/swagger-ui.html",
                // "/**"
            )
            .permitAll()
            .anyRequest()
            .authenticated()
        )
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf->csrf.disable())
        .httpBasic(httpBasic->httpBasic.disable())
        .formLogin(form->form.disable())
        .oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
        .build();
    }
}
