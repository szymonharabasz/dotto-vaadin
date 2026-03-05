package com.szymonharabasz;

import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/images/*.png").permitAll()
                .requestMatchers("/icons/*.svg").permitAll()
        );

        http.with(VaadinSecurityConfigurer.vaadin(), configurer ->
            configurer.oauth2LoginPage(
                    "/oauth2/authorization/keycloak"
            )
        );

        return http.build();
    }
}
