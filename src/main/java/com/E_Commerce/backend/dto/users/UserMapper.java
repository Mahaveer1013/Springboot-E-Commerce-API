package com.E_Commerce.backend.dto.users;

import com.E_Commerce.backend.lib.enums.UserRole;
import com.E_Commerce.backend.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    // Converts UserDto to User entity
    public Users toEntity(UserRequest userRequest) {
        var user = new Users();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(UserRole.valueOf(userRequest.getRole().toString().toUpperCase()));

        return user;
    }

    // Converts User entity to UserDto
    public UserResponse toResponse(Users user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(UserRole.valueOf(user.getRole().toString().toUpperCase()));

        return userResponse;
    }
}
