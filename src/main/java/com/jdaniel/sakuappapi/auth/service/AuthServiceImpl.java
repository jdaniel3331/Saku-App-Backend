package com.jdaniel.sakuappapi.auth.service;

import com.jdaniel.sakuappapi.auth.model.AccessToken;
import com.jdaniel.sakuappapi.auth.model.dto.LogInRequest;
import com.jdaniel.sakuappapi.user.model.User;
import com.jdaniel.sakuappapi.auth.model.UserCredential;
import com.jdaniel.sakuappapi.auth.model.dto.RegisterRequest;
import com.jdaniel.sakuappapi.auth.repository.UserCredentialRepository;
import com.jdaniel.sakuappapi.user.repository.UserRepository;
import com.jdaniel.sakuappapi.common.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AccessTokenServiceImpl accessTokenService;

    @Override
    public String register(RegisterRequest registerRequest) {
        if(userCredentialRepository.existsByEmailIgnoreCase(registerRequest.email())) {
            throw new RequestException(HttpStatus.CONFLICT.name(), "El usuario "+registerRequest.email()+" ya se encuentra registrado", HttpStatus.CONFLICT.value());
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

    @Override
    public String logIn(LogInRequest logInRequest) {
        String email = logInRequest.email();
        String password = logInRequest.password();

        Authentication authentication = authenticateUser(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

         AccessToken newToken = accessTokenService.saveToken(accessTokenService.createToken(authentication),
                userRepository.getUserByEmailIgnoreCase(email)
                        .orElseThrow(
                                () -> new RequestException(HttpStatus.NOT_FOUND.name(), "Usuario no encontrado", HttpStatus.NOT_FOUND.value())
                        )
        );

        return newToken.getAccessToken();
    }

    private Authentication authenticateUser(String email, String password){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails == null){
            throw new RequestException(HttpStatus.BAD_REQUEST.name(), "Usuario o contraseña incorrecto",HttpStatus.BAD_REQUEST.value());
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new RequestException(HttpStatus.BAD_REQUEST.name(), "Contraseña incorrecta",HttpStatus.BAD_REQUEST.value());
        }

        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
