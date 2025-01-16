package com.E_Commerce.backend.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    String username;
    String password;
}
