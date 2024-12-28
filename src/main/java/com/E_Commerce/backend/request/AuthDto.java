package com.E_Commerce.backend.request;

import jakarta.validation.constraints.NotNull;

public record AuthDto(
        @NotNull(message = "Username or Email is required")
        String username,

        @NotNull(message = "Password is required")
        String password
) {

}
