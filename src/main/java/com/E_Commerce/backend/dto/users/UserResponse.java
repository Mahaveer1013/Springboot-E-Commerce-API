package com.E_Commerce.backend.dto.users;

import com.E_Commerce.backend.lib.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private UserRole role;
}
