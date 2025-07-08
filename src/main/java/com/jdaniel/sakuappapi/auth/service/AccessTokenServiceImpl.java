package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.AccessToken;
import com.jdaniel.sakuappapi.auth.repository.AccessTokenRepository;
import com.jdaniel.sakuappapi.common.util.JwtUtility;
import com.jdaniel.sakuappapi.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{

    private final JwtUtility jwtUtility;
    private final AccessTokenRepository accessTokenRepository;

    public AccessTokenServiceImpl(JwtUtility jwtUtility, AccessTokenRepository accessTokenRepository) {
        this.jwtUtility = jwtUtility;
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public String createToken(Authentication auth) {
        return jwtUtility.generateToken(auth);
    }

    @Override
    public AccessToken saveToken(String token, User user) {
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(token);
        accessToken.setUser(user);
        accessToken.setRevoked(false);
        accessToken.setExpired(false);

        return accessTokenRepository.save(accessToken);
    }

    @Override
    public void invalidateTokens(String email) {
        accessTokenRepository.invalidateTokensByUserEmail(email);
    }
}
