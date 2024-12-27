package com.E_Commerce.backend.dto.users;

import jakarta.validation.constraints.NotNull;

public record UserResponseDto(
        Long id,

        @NotNull(message = "Email cannot be null")
        String email,

        @NotNull(message = "Role cannot be null")
        String role
) {
}