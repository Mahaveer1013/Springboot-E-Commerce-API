package com.E_Commerce.backend;

import com.E_Commerce.backend.lib.authConfig.JWTService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class BackendApplication {
    private static final JWTService jwtService = new JWTService();

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(BackendApplication.class, args);
    }

}
