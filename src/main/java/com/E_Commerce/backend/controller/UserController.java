package com.E_Commerce.backend.controller;

import com.E_Commerce.backend.lib.authConfig.JwtPayload;
import com.E_Commerce.backend.lib.authConfig.UserPrincipal;
import com.E_Commerce.backend.request.UserDto;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;
        return ResponseEntity.status(200).body(new ApiResponse(null, new JwtPayload(userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getRole())));
    }

    @GetMapping("/all-users")
    public ResponseEntity<ApiResponse> getAllUser(@PageableDefault(page = 0, size = 10) Pageable page) {
        return userService.getAllUser(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}
