package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.User;
import com.jdaniel.sakuappapi.auth.model.UserCredential;
import com.jdaniel.sakuappapi.auth.model.dto.RegisterRequest;
import com.jdaniel.sakuappapi.auth.repository.UserCredentialRepository;
import com.jdaniel.sakuappapi.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest registerRequest) {
        if(userCredentialRepository.existsByEmailIgnoreCase(registerRequest.email())) {
            return "El usuario ya se encuentra registrado";
        }

        User newUser = new User();
        newUser.setFirstName(registerRequest.firstName());
        newUser.setSecondName(registerRequest.secondName());
        newUser.setFirstSurname(registerRequest.firstSurname());
        newUser.setSecondSurname(registerRequest.secondSurname());
        newUser.setDateOfBirth(registerRequest.dateOfBirth());
        newUser.setCreatedAt(LocalDate.now());
        newUser.setActive(true);

        User savedUser = userRepository.save(newUser);

        UserCredential userCredential = new UserCredential();
        userCredential.setEmail(registerRequest.email());
        userCredential.setPassword(passwordEncoder.encode(registerRequest.password()));
        userCredential.setUser(savedUser);

        userCredentialRepository.save(userCredential);

        return "Usuario registrado correctamente";
    }
}
