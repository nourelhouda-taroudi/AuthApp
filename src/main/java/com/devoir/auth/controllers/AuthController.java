package com.devoir.auth.controllers;

import com.devoir.auth.models.User;
import com.devoir.auth.models.requests.LoginRequest;
import com.devoir.auth.models.requests.RegisterRequest;
import com.devoir.auth.services.AuthService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    public User signup(@RequestBody @Valid RegisterRequest registerRequest) {
        // Create user using userService + encrypting password using Spring security
        return null;
    }
    @PostMapping("/login")
    public ResponseEntity<?>createAuthenticationToken(@RequestBody @Valid LoginRequest loginRequest){
        // Check email and password is valid (If email exist in database & password is corect)
        // Generate Token
        return ResponseEntity.ok("Ok");
    }
}
