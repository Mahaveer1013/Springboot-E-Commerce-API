package com.E_Commerce.backend.lib.authConfig;

import com.E_Commerce.backend.lib.enums.UserRole;
import lombok.Data;

@Data
public class JwtPayload {
    private Long id;
    private String username;
    private UserRole role;

    public JwtPayload(Long id, String username, UserRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

}
