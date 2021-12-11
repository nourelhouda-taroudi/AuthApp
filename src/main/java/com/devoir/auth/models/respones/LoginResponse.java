package com.devoir.auth.models.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    @Setter private String jwt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Setter private String name;
    @Setter private String email;
}
