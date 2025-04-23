package com.jdaniel.sakuappapi.security;

import com.jdaniel.sakuappapi.util.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApiSecurityConfig {
    @Autowired
    private EncryptionService encryptionService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return encryptionService;
    }
}
