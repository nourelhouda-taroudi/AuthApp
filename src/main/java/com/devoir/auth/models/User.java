package com.devoir.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    @NotEmpty(message="Nom est obligatoire.")
    protected String name;

    @NotEmpty(message="Email est obligatoire.")
    protected String email;

    @NotEmpty(message="Mot de passe est obligatoire.")
    @JsonIgnore
    protected String password;

    protected String phone;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Builder.Default
    @Column(columnDefinition = "boolean default true")
    protected boolean accountNonLocked=true;

    @Builder.Default
    @Column(columnDefinition = "boolean default true")
    protected boolean enabled=true;

    @Builder.Default
    @Column(columnDefinition = "boolean default true")
    protected boolean credentialsNonExpired=true;


    public User() {
        super();
    };


}
