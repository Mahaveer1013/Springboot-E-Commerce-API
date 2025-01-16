package com.E_Commerce.backend.controller;

import com.E_Commerce.backend.dto.users.UserRequest;
import com.E_Commerce.backend.dto.users.UserResponse;
import com.E_Commerce.backend.lib.roleAnnotation.Admin;
import com.E_Commerce.backend.lib.roleAnnotation.Seller;
import com.E_Commerce.backend.model.PaginatedResponse;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.auth.AuthService;
import com.E_Commerce.backend.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    @Admin
    public ResponseEntity<ApiResponse> getAllUser(@PageableDefault(page = 0, size = 10) Pageable page) {

        PaginatedResponse<UserResponse> paginatedUser = userService.getAllUser(page);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, paginatedUser)
        );
    }
    
    @GetMapping("/{id}")
    @Admin
    public ResponseEntity<ApiResponse> getUser(@PathVariable("id") Long id) {
        UserResponse user = userService.finduser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, user)
        );
    }

    @GetMapping("/is-seller")
    @Seller
    public ResponseEntity<ApiResponse> isSeller() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(null, authService.getCurrentUser()));
    }

    @GetMapping("/is-buyer")
    @PreAuthorize("hasAuthority('BUYER')")
    public ResponseEntity<ApiResponse> isBuyer() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(null, authService.getCurrentUser()));
    }

    @GetMapping("/is-admin")
    @Admin
    public ResponseEntity<ApiResponse> isAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(null, authService.getCurrentUser()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Updated Successfully", userResponse));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Deleted Successfully", null));
    }

}
