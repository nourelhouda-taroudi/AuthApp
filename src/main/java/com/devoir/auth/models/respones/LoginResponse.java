package com.devoir.auth.models.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Builder
public class LoginResponse {
    private final String jwt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Setter private String firstname;
    @Setter private String lastname;
    @Setter private Long contactId;
    @Setter private String email;
}
