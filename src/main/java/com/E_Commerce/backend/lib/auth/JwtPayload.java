package com.E_Commerce.backend.lib.auth;

import lombok.Data;

@Data
public class JwtPayload {
    //    private Long id;
    private String username;
    private String email;
    private String role;

    public JwtPayload(String username, String email, String role) {
//        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

}
