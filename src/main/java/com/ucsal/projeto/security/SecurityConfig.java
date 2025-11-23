package com.ucsal.projeto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        // Permite POST para /solicitar apenas para usuários autenticados (professores)
                        .requestMatchers(HttpMethod.POST, "/api/projetos/solicitar").authenticated()

                        // Endpoints que só podem ser acessados por Administradores (2.4, 1.1, 1.2, 1.3)
                        .requestMatchers(HttpMethod.GET, "/api/projetos").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/projetos/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/projetos/**").hasRole("ADMINISTRADOR")

                        // Qualquer outra requisição deve ser autenticada por padrão
                        .anyRequest().authenticated()
                )
                // Configura o login básico (em um projeto real, seria um filtro JWT)
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}