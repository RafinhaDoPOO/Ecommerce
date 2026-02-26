package com.example.demo.service;

import com.example.demo.model.User; // Ajuste para o seu pacote
import com.example.demo.repository.UserRepository; // Ajuste para o seu pacote
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Converte o usuário do seu banco para o usuário do Spring Security
        // O SimpleGrantedAuthority é o que faz o sec:authorize="hasRole('ADMIN')" funcionar!
        return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        // Usamos List.of() que é mais moderno, curto e não dá erro de digitação!
        java.util.List.of(new SimpleGrantedAuthority(user.getRole()))
);
}
}