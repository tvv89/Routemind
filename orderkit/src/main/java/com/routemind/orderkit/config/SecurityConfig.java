package com.routemind.orderkit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class responsible for setting up security configurations related to the Spring Security framework.
 * This class includes the configuration for OAuth2 Resource Server and defines which URLs are permitted without
 * authentication.
 *
 * @author Volodymyr Tymchuk
 * @version 1.0
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    private String[] permitAllUrls = {
            "/swagger-ui/**",
            "/v1/api-routemind-docs/**",
            "/actuator/**"
    };

    /**
     * Configures the security filter chain for the application, specifying which URLs are permitted without authentication
     * and setting up OAuth2 Resource Server with JWT token decoding.
     *
     * @param http The HttpSecurity object used to configure security.
     * @return The configured SecurityFilterChain for the application.
     * @throws Exception If there is an error during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth-> auth
                        .requestMatchers(permitAllUrls).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2->
                        oauth2.jwt(jwt->jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))))
                .build();
    }
}