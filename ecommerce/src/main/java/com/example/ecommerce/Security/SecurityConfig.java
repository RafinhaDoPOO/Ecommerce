package com.example.ecommerce.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN") // Apenas admin pode acessar /admin
                .requestMatchers("/user/**").hasRole("USER")   // Apenas user pode acessar /user
                .anyRequest().authenticated()                 // Todas as outras rotas exigem autenticação
            )
            .formLogin(withDefaults());                       // Habilita login com formulário
        return http.build();
    }
}