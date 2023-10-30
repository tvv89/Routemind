package com.routemind.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for setting up the authentication provider in Spring Security.
 * This class defines a custom authentication provider that uses a UserDetailsService
 * and a BCryptPasswordEncoder for authentication.
 */
@Configuration
@RequiredArgsConstructor
public class AuthProviderConfig {
    private final UserDetailsService userDetailsService;

    /**
     * Creates and configures a DaoAuthenticationProvider bean to be used for authentication.
     *
     * @return A configured DaoAuthenticationProvider instance.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
}