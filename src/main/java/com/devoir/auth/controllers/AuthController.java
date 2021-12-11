package com.devoir.auth.controllers;

import com.devoir.auth.exceptions.LoginException;
import com.devoir.auth.models.User;
import com.devoir.auth.models.requests.LoginRequest;
import com.devoir.auth.models.requests.RegisterRequest;
import com.devoir.auth.models.respones.LoginResponse;
import com.devoir.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User signup(@RequestBody @Valid RegisterRequest registerRequest) {
        // Create user using userService + encrypting password using Spring security
        User user = authService.register(registerRequest);
        return user;
    }
    @PostMapping("/login")
    public ResponseEntity<?>createAuthenticationToken(@RequestBody @Valid LoginRequest loginRequest){
        // Check email and password is valid (If email exist in database & password is corect)
        // Generate Token
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (LoginException e){
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
