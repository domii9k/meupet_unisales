package com.meupet.api.Configuration.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Acesso público (ADMIN, SECRETARIO e VETERINARIO) para login e registro
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registro").permitAll()

                        // Permissões para Veterinários e Secretários
                        .requestMatchers(HttpMethod.POST, "/animais/novo-animal", "/proprietarios/novo-proprietario").hasAnyRole("VETERINARIO", "SECRETARIO")
                        .requestMatchers(HttpMethod.GET, "/animais", "/usuarios", "/proprietarios", "/historico-peso-altura").hasAnyRole("VETERINARIO", "SECRETARIO")
                        .requestMatchers(HttpMethod.GET, "/animais/{id}", "/usuarios/{id}", "/proprietarios/{id}", "/historico-peso-altura/{id}").hasAnyRole("VETERINARIO", "SECRETARIO")
                        .requestMatchers(HttpMethod.DELETE, "/animais/{id}", "/usuarios/{id}", "/proprietarios/{id}").hasAnyRole("VETERINARIO", "SECRETARIO")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}", "/animais/{id}", "/proprietarios/{id}").hasAnyRole("VETERINARIO", "SECRETARIO")

                        // Permissões exclusivas para Veterinários
                        .requestMatchers(HttpMethod.POST, "/vacinas/nova-vacina", "/historico-peso-altura/novo-historico", "/vacinacoes/nova-vacinacao").hasRole("VETERINARIO")
                        .requestMatchers(HttpMethod.GET, "/vacinacoes", "/vacinas").hasRole("VETERINARIO")
                        .requestMatchers(HttpMethod.GET, "/vacinacoes/{id}", "/vacinas/{id}").hasRole("VETERINARIO")
                        .requestMatchers(HttpMethod.DELETE, "/vacinacoes/{id}", "/vacinas/{id}", "/historico-peso-altura/{id}").hasRole("VETERINARIO")
                        .requestMatchers(HttpMethod.PUT, "/vacinacoes/{id}", "/vacinas/{id}").hasRole("VETERINARIO")

                        // Qualquer outra requisição exige autenticação
                        .anyRequest().authenticated()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
