package com.foliaco.bathrooms.infrastructure.config;

import com.foliaco.bathrooms.infrastructure.security.JwtAuthFilter;
import com.foliaco.bathrooms.infrastructure.security.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.foliaco.bathrooms.infrastructure.repository")
@EnableEnversRepositories(basePackages = "com.foliaco.bathrooms.infrastructure.repository")
@RequiredArgsConstructor
public class AppConfig {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtAuthenticationProvider);
    }

}
