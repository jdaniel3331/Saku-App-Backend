package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.UserCredential;
import com.jdaniel.sakuappapi.auth.model.dto.CustomUserDetails;
import com.jdaniel.sakuappapi.auth.repository.UserCredentialRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCredentialRepository userCredentialRepository;

    public CustomUserDetailsService(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential credential = userCredentialRepository
                .getUserCredentialByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Long userId = credential.getUser().getUserId();
        List<SimpleGrantedAuthority> authorities = Collections.emptyList();
        return new CustomUserDetails(
                userId,
                credential.getEmail(),
                credential.getPassword(),
                authorities
        );
    }
}
