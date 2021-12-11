package com.devoir.auth.services;

import com.devoir.auth.exceptions.LoginException;
import com.devoir.auth.models.User;
import com.devoir.auth.models.requests.LoginRequest;
import com.devoir.auth.models.requests.RegisterRequest;
import com.devoir.auth.models.respones.LoginResponse;

public interface AuthService {
    User register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest) throws LoginException;
}
