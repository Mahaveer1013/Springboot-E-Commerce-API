package com.E_Commerce.backend.request;

import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull(message = "Username can't be null")
        String username,
        
        @NotNull(message = "Password can't be null")
        String password
) {
}
