package com.E_Commerce.backend.dto.auth;

import com.E_Commerce.backend.lib.auth.UserPrincipal;
import com.E_Commerce.backend.lib.enums.UserRole;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {
    public AuthResponse toResponse(UserPrincipal userDetails) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(userDetails.getId());
        authResponse.setUsername(userDetails.getUsername());
        authResponse.setEmail(userDetails.getEmail());
        authResponse.setRole(UserRole.valueOf(userDetails.getRole()));
        return authResponse;
    }
}
