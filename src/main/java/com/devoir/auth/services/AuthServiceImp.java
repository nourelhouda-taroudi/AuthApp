package com.devoir.auth.services;

import com.devoir.auth.models.User;
import com.devoir.auth.models.requests.LoginRequest;
import com.devoir.auth.models.requests.RegisterRequest;
import com.devoir.auth.models.respones.LoginResponse;

public class AuthServiceImp implements AuthService{


    @Override
    // Create new user
    public User register(RegisterRequest registerRequest) {
        // Build user
        // Encrypt password
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }
}
