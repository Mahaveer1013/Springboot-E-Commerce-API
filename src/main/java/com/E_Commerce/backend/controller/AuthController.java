package com.E_Commerce.backend.controller;

import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.request.UserDto;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDto user) {
        return service.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Users user) {
        return service.verify(user);
    }

}