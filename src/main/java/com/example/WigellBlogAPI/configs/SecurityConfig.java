package com.example.WigellBlogAPI.configs;

import com.example.WigellBlogAPI.converters.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Autowired
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    //TODO uppdatera classen för Keycloak
/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                        .csrf(csrf ->csrf.disable())
                        .headers(h -> h.frameOptions(f -> f.disable())) //Så inte H2-sidan ska blockeras.
                        .formLogin(Customizer.withDefaults());
        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/v2/newpost").hasRole("wigellblog-user")
                                .requestMatchers("/api/v2/updatepost").hasAnyRole("wigellblog-user", "wigellblog-admin")
                                .requestMatchers("/api/v2/deletepost/").hasAnyRole("wigellblog-user", "wigellblog-admin")
                                .requestMatchers("/api/v2/count").hasRole("wigellblog-admin")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2
                                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        return http.build();
    }


}
