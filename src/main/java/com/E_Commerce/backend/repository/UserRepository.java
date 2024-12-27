package com.E_Commerce.backend.repository;

import com.E_Commerce.backend.model.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    Users findByUsername(String username);

    boolean existsByUsername(
            @NotNull(message = "Username cannot be null")
            String username
    );

    boolean existsByEmail(
            @NotNull(message = "Email cannot be null")
            @Email(message = "Email should be valid")
            String email
    );
}
