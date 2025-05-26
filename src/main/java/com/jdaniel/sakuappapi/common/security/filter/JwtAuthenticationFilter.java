package com.jdaniel.sakuappapi.common.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jdaniel.sakuappapi.auth.model.AccessToken;
import com.jdaniel.sakuappapi.auth.model.dto.CustomUserDetails;
import com.jdaniel.sakuappapi.auth.repository.AccessTokenRepository;
import com.jdaniel.sakuappapi.common.util.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            AccessToken foundToken = accessTokenRepository.findByAccessTokenIgnoreCase(token).orElse(null);

            if(foundToken == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(String.format("{\"error\": \"%s\", \"message\": \"%s\"}", HttpServletResponse.SC_UNAUTHORIZED, "Token not found"));
                return;
            }

            if(foundToken.isExpired() && foundToken.isRevoked()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(String.format("{\"error\": \"%s\", \"message\": \"%s\"}", HttpServletResponse.SC_UNAUTHORIZED, "Token expired"));
                return;
            }
            try{

                DecodedJWT decodedToken = jwtUtility.validateToken(token);
                String email = decodedToken.getClaim("email").asString();
                Long userId = decodedToken.getClaim("user_id").asLong();

                CustomUserDetails userDetails = new CustomUserDetails(userId, email, null, null);

                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContext securityContext = SecurityContextHolder.getContext();

                securityContext.setAuthentication(auth);
                SecurityContextHolder.setContext(securityContext);

            }catch (JWTVerificationException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(String.format("{\"error\": \"%s\", \"message\": \"%s\"}", HttpServletResponse.SC_UNAUTHORIZED, e.getMessage()));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
