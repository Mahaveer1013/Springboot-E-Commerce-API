package com.E_Commerce.backend.controller;

import com.E_Commerce.backend.dto.auth.AuthRequest;
import com.E_Commerce.backend.dto.users.UserRequest;
import com.E_Commerce.backend.dto.users.UserResponse;
import com.E_Commerce.backend.lib.auth.JwtPayload;
import com.E_Commerce.backend.lib.auth.UserPrincipal;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.auth.AuthService;
import com.E_Commerce.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User added successfully", userResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.verify(authRequest);
        if (token == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Credentials not matched", null));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Token generated successfully", token));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;
        UserResponse userResponse = userService.finduser(((UserPrincipal) userDetails).getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, new JwtPayload(
                        userPrincipal.getId(),
                        userResponse.getUsername(),
                        userResponse.getEmail(),
                        userResponse.getRole().toString())
                )
        );
    }

}