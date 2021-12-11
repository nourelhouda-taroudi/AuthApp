package com.devoir.auth.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterRequest {

    @NotEmpty(message = "Le nom est obligatoire.")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères.")
    private String name;

    @NotEmpty(message = "Mot de passe est obligatoire.")
    private String password;

    @NotEmpty(message = "L'email est obligatoire.")
    //@UniqueEmail(message="Cet email personnel existe déjà")
    @Email(message="Email incorrect.")
    @Size(max = 50, message = "L'email ne doit pas dépasser 50 caractères.")
    private String email;

    private String phone;
}
