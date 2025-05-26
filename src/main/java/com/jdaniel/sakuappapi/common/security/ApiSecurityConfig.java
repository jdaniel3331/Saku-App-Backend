package com.jdaniel.sakuappapi.common.security;

import com.jdaniel.sakuappapi.auth.service.CustomUserDetailsService;
import com.jdaniel.sakuappapi.auth.service.LogOutService;
import com.jdaniel.sakuappapi.common.security.filter.JwtAuthenticationFilter;
import com.jdaniel.sakuappapi.common.util.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ApiSecurityConfig {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    @Lazy
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private LogOutService logOutService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/health").permitAll()

                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/logIn").permitAll()

                        .requestMatchers(HttpMethod.GET,"/tasks/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/tasks").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/tasks/{taskId}").authenticated()
                        .requestMatchers(HttpMethod.PATCH,"/tasks/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/auth/log-out").authenticated()
                        .anyRequest().permitAll()
                )
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        http
                .logout(logout -> logout
                        .logoutUrl("/auth/log-out")
                        .addLogoutHandler(logOutService)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()
                        )
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return encryptionService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }
    @Bean
    public CustomUserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
