package com.devoir.auth.services;

import com.devoir.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);
}
