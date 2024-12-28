package com.E_Commerce.backend.mapper;

import com.E_Commerce.backend.dto.users.UserResponseDto;
import com.E_Commerce.backend.lib.enums.UserRole;
import com.E_Commerce.backend.lib.exception.InvalidEnumException;
import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.request.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    // Converts UserDto to User entity
    public Users userDto_to_user(UserDto dto) {
        var user = new Users();

        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.username() != null) user.setUsername(dto.username());
        if (dto.password() != null) user.setPassword(dto.password());
        if (dto.role() != null) {
            try {
                user.setRole(UserRole.valueOf(dto.role().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InvalidEnumException("Invalid role provided");
            }
        }

        return user;
    }

    // Converts User entity to UserDto
    public UserResponseDto user_to_userDto(Users user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getRole().name()
        );
    }
}
