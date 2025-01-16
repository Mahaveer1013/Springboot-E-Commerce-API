package com.E_Commerce.backend.dto.auth;

import com.E_Commerce.backend.lib.enums.UserRole;
import lombok.Data;

@Data
public class AuthResponse {
    private Long id;
    private String username;
    private String email;
    private UserRole role;
}
