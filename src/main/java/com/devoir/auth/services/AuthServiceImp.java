package com.devoir.auth.services;

import com.devoir.auth.exceptions.LoginException;
import com.devoir.auth.models.User;
import com.devoir.auth.models.requests.LoginRequest;
import com.devoir.auth.models.requests.RegisterRequest;
import com.devoir.auth.models.respones.LoginResponse;
import com.devoir.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final UserJpaRepository userJpaRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    // Create new user
    public User register(RegisterRequest registerRequest) {
        // Build user & Encrypt password
        User user = User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .build();
        userJpaRepository.save(user);
        
        return user;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws LoginException {
        if (loginRequest.getEmail() == null ||
                loginRequest.getPassword() == null ||
                loginRequest.getEmail().isBlank() ||
                loginRequest.getPassword().isBlank()) {
            throw new LoginException("Email et mot de passe sont obligatoires.");
        }


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final User user = userJpaRepository.findFirstByEmail(loginRequest.getEmail()).orElseThrow(()->new UsernameNotFoundException("Not found" ));

        final String jwt= jwtUtil.generateToken(user);

        LoginResponse loginResponse =new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setJwt(jwt);

        return loginResponse;
    }
}
