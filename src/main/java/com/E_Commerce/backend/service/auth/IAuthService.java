package com.E_Commerce.backend.service.auth;

import com.E_Commerce.backend.dto.auth.AuthRequest;
import com.E_Commerce.backend.dto.auth.AuthResponse;

public interface IAuthService {

    AuthResponse getCurrentUser();

    String verify(AuthRequest user);
}
