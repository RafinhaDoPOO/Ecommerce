package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
    .requestMatchers("/register", "/error").permitAll() // TEM QUE SER O PRIMEIRO
    .requestMatchers("/products/list", "/products/{id}", "/products/edit/**").permitAll() 
    .requestMatchers("/api/products/**").permitAll()
    .requestMatchers("/cart/**").permitAll() 
    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
    .anyRequest().authenticated() // TEM QUE SER O ÚLTIMO

        )
        .formLogin(withDefaults())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/products/list")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .permitAll()
        )
        .httpBasic(withDefaults());

    return http.build();
}
}