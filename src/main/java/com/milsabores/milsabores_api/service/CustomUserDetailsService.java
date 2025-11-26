package com.milsabores.milsabores_api.service;

import com.milsabores.milsabores_api.repository.UserRepository;
import com.milsabores.milsabores_api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Vamos a usar el EMAIL como "username" de Spring
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())       // identificador
                .password(user.getPassword())        // password encriptado
                .authorities(user.getRol())          // "ADMIN", "USER"
                .build();
    }
}
