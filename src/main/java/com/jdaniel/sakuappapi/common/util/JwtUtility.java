package com.jdaniel.sakuappapi.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jdaniel.sakuappapi.auth.model.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtility {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.token.expireTimeinSeconds}")
    private int expirationTime;

    public String generateToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = authentication.getPrincipal().toString();

        return JWT
                .create()
                .withIssuer(this.issuer)
                .withSubject(email)
                .withClaim("email", userDetails.getUsername())
                .withClaim("user_id", userDetails.getUserId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expirationTime)))
                .withNotBefore(new Date(System.currentTimeMillis()))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .withIssuer(this.issuer)
                    .build();
            return verifier.verify(token);
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token is invalid or expired");
        }
    }
}
