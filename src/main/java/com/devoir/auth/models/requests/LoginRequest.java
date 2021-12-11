package com.devoir.auth.models.requests;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
