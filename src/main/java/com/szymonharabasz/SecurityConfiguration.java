package com.szymonharabasz;

import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapperForKeycloak() {
        return authorities -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            var authority = authorities.iterator().next();

            if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                var userInfo = oidcUserAuthority.getUserInfo();

                if (userInfo.hasClaim("resource_access")) {
                    var resourceAccess = userInfo.getClaimAsMap("resource_access");
                    if (resourceAccess.containsKey("dotto-vaadin")) {
                        var dottoVaadinObject = resourceAccess.get("dotto-vaadin");
                        if (dottoVaadinObject instanceof HashMap<?, ?> dottoVaadin &&
                                dottoVaadin.containsKey("roles") &&
                                dottoVaadin.get("roles") instanceof Collection<?> roles) {
                            mappedAuthorities.addAll(roles.stream()
                                    .map(role ->
                                            new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())
                                    )
                                    .toList());


                        }
                    }
                }
            }
            return mappedAuthorities;
        };
    }
}
