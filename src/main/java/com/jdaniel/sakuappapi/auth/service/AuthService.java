package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest registerRequest);

}
