package com.E_Commerce.backend.request;

import jakarta.validation.constraints.NotNull;

public record UserDto(
        Long id,

        @NotNull(message = "Email cannot be null")
        String email,

        @NotNull(message = "Username cannot be null")
        String username,

        String password,

        @NotNull(message = "Role cannot be null")
        String role
) {
}



