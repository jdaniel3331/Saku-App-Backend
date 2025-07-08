package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.repository.AccessTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogOutService implements LogoutHandler {

    public LogOutService(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            accessTokenRepository.invalidateToken(token);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            try {
                response.getWriter().write(String.format("{\"message\": \"%s\"}", "Logout successful"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            try {
                response.getWriter().write(String.format("{\"error\": \"%s\", \"message\": \"%s\"}", HttpServletResponse.SC_UNAUTHORIZED, "Token not found on header"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
