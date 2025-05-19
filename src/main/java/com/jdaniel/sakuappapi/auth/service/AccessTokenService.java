package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.AccessToken;
import com.jdaniel.sakuappapi.user.model.User;
import org.springframework.security.core.Authentication;

public interface AccessTokenService {

    String createToken(Authentication auth);
    AccessToken saveToken(String token, User user);
    void invalidateTokens(String email);
}
