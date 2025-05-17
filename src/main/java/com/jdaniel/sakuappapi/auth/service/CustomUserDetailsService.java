package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.UserCredential;
import com.jdaniel.sakuappapi.auth.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential credential = userCredentialRepository
                .getUserCredentialByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        List<SimpleGrantedAuthority> authorities = Collections.emptyList();
        return new User(
                credential.getEmail(),
                credential.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
